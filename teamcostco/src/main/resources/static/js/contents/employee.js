document.addEventListener("DOMContentLoaded", function() {
    const filters = {
        empId: document.getElementById("filter-emp-id"),
        empName: document.getElementById("filter-emp-name"),
        empEmail: document.getElementById("filter-emp-email"),
        phoneNumber: document.getElementById("filter-phone"),
        hireDate: document.getElementById("filter-hire-date"),
        joinDate: document.getElementById("filter-join-date"),
        jobTitle: document.getElementById("filter-job-title-dropdown")
    };

    const tableRows = document.querySelectorAll("#employee-table-body tr");

    // 필터 입력 필드와 드롭다운에 이벤트 리스너 추가
    Object.values(filters).forEach(filter => {
        filter.addEventListener("input", filterTable);
        filter.addEventListener("change", filterTable);
    });

    function filterTable() {
        tableRows.forEach(row => {
            let showRow = true;

            // 텍스트 입력 필드 필터링
            showRow = showRow && filterByTextInputs(row);

            // 드롭다운 필터링
            showRow = showRow && filterByDropdowns(row);

            row.style.display = showRow ? "" : "none";
        });
    }

    function filterByTextInputs(row) {
        return Object.keys(filters).filter(key => key !== "jobTitle").every(key => {
            const cellIndex = getColumnIndex(key);
            const cell = row.querySelector(`td:nth-child(${cellIndex})`);
            const cellValue = cell ? cell.textContent.toLowerCase().trim() : '';
            const filterValue = filters[key].value.toLowerCase().trim();

            return !filterValue || cellValue.includes(filterValue);
        });
    }

    function filterByDropdowns(row) {
        const jobTitleCell = row.querySelector(`td:nth-child(${getColumnIndex('jobTitle')})`);
        const jobTitleValue = jobTitleCell ? jobTitleCell.textContent.trim() : '';
        const selectedJobTitle = filters.jobTitle.value;

        return !selectedJobTitle || jobTitleValue === selectedJobTitle;
    }

    function getColumnIndex(key) {
        const columnOrder = ['empId', 'empName', 'empEmail', 'phoneNumber', 'jobTitle', 'hireDate', 'joinDate'];
        return columnOrder.indexOf(key) + 1;
    }

    // 드롭다운 옵션을 동적으로 추가
    fetch('/api/job-titles')
        .then(response => response.json())
        .then(data => {
            const dropdown = filters.jobTitle;
            data.forEach(jobTitle => {
                const option = document.createElement('option');
                option.value = jobTitle;
                option.textContent = jobTitle;
                dropdown.appendChild(option);
            });
        })
        .catch(error => console.error('Error loading job titles:', error));
});