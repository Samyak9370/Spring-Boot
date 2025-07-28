document.addEventListener('DOMContentLoaded', () => {

    const API_URL = '/employees';

    const modal = document.getElementById('employee-modal');
    const addEmployeeBtn = document.getElementById('add-employee-btn');
    const closeBtn = document.querySelector('.close-btn');
    const employeeForm = document.getElementById('employee-form');
    const employeeTableBody = document.querySelector('#employee-table tbody');
    const modalTitle = document.getElementById('modal-title');
    const employeeIdField = document.getElementById('employee-id');
    const loadingSpinner = document.getElementById('loading-spinner');

    // Show modal
    addEmployeeBtn.onclick = () => {
        modal.style.display = 'block';
        modalTitle.innerText = 'Add Employee';
        employeeForm.reset();
        employeeIdField.value = '';
    }

    // Hide modal
    closeBtn.onclick = () => {
        modal.style.display = 'none';
    }
    window.onclick = (event) => {
        if (event.target == modal) {
            modal.style.display = 'none';
        }
    }

    // Fetch and display all employees
    const fetchEmployees = async () => {
        loadingSpinner.classList.add('show');
        employeeTableBody.innerHTML = '';
        try {
            const response = await fetch(API_URL);
            if (!response.ok) throw new Error('Network response was not ok');
            const employees = await response.json();

            // Infer properties if not present in all objects
            const sampleEmployee = employees.length > 0 ? employees[0] : {};
            const nameKey = Object.keys(sampleEmployee).find(k => k.toLowerCase().includes('name')) || 'name';
            const emailKey = Object.keys(sampleEmployee).find(k => k.toLowerCase().includes('email')) || 'email';
            const phoneKey = Object.keys(sampleEmployee).find(k => k.toLowerCase().includes('phone')) || 'phone';

            if (employees.length === 0) {
                const row = `<tr><td colspan="5" style="text-align:center;">No employees found.</td></tr>`;
                employeeTableBody.innerHTML = row;
            } else {
                employees.forEach(employee => {
                    const row = `
                        <tr>
                            <td>${employee.id}</td>
                            <td>${employee[nameKey] || 'N/A'}</td>
                            <td>${employee[emailKey] || 'N/A'}</td>
                            <td>${employee[phoneKey] || 'N/A'}</td>
                            <td>
                                <button class="action-btn edit-btn" data-id="${employee.id}"><i class="fas fa-edit"></i></button>
                                <button class="action-btn delete-btn" data-id="${employee.id}"><i class="fas fa-trash-alt"></i></button>
                            </td>
                        </tr>
                    `;
                    employeeTableBody.innerHTML += row;
                });
            }
        } catch (error) {
            showToast('Error fetching employees.', 'error');
            console.error('Fetch error:', error);
        } finally {
            loadingSpinner.classList.remove('show');
        }
    };

    // Handle form submission (Add/Edit)
    employeeForm.addEventListener('submit', async (event) => {
        event.preventDefault();

        const id = employeeIdField.value;
        const employeeData = {
            // Your Employee model might have different field names. Adjust here if needed.
            name: document.getElementById('name').value,
            email: document.getElementById('email').value,
            phone: document.getElementById('phone').value,
        };

        const method = id ? 'PUT' : 'POST';
        const url = id ? `${API_URL}/${id}` : API_URL;

        try {
            const response = await fetch(url, {
                method: method,
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(employeeData),
            });

            if (!response.ok) throw new Error(`Server responded with status: ${response.status}`);
            
            const resultMessage = await response.text();
            showToast(resultMessage, 'success');
            
            modal.style.display = 'none';
            fetchEmployees();
        } catch (error) {
            showToast('An error occurred.', 'error');
            console.error('Submit error:', error);
        }
    });

    // Handle Edit and Delete button clicks
    employeeTableBody.addEventListener('click', async (event) => {
        const target = event.target.closest('.action-btn');
        if (!target) return;

        const id = target.dataset.id;

        // Edit button
        if (target.classList.contains('edit-btn')) {
            try {
                const response = await fetch(`${API_URL}/${id}`);
                if (!response.ok) throw new Error('Could not fetch employee details.');
                const employee = await response.json();
                
                // Populate the form for editing
                modalTitle.innerText = 'Edit Employee';
                employeeIdField.value = employee.id;
                 // Infer keys again for safety
                const nameKey = Object.keys(employee).find(k => k.toLowerCase().includes('name')) || 'name';
                const emailKey = Object.keys(employee).find(k => k.toLowerCase().includes('email')) || 'email';
                const phoneKey = Object.keys(employee).find(k => k.toLowerCase().includes('phone')) || 'phone';
                
                document.getElementById('name').value = employee[nameKey] || '';
                document.getElementById('email').value = employee[emailKey] || '';
                document.getElementById('phone').value = employee[phoneKey] || '';

                modal.style.display = 'block';

            } catch (error) {
                showToast(error.message, 'error');
            }
        }

        // Delete button
        if (target.classList.contains('delete-btn')) {
            if (confirm('Are you sure you want to delete this employee?')) {
                try {
                    const response = await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
                    if (!response.ok) throw new Error('Failed to delete.');
                    
                    const resultMessage = await response.text();
                    showToast(resultMessage, 'success');
                    fetchEmployees();

                } catch (error) {
                    showToast(error.message, 'error');
                }
            }
        }
    });

    // Show toast notification
    function showToast(message, type = 'success') {
        const toast = document.getElementById('toast');
        toast.textContent = message;
        toast.className = `show ${type}`;
        setTimeout(() => { toast.className = toast.className.replace('show', ''); }, 3000);
    }

    // Initial load
    fetchEmployees();
});