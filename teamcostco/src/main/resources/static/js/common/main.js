function setTableColumnWidths(table, widths) {
    if (!table || !widths || !Array.isArray(widths)) {
        throw new Error('Invalid arguments');
    }

    // Ensure that the table has a thead and tbody
    const thead = table.querySelector('thead');
    const tbody = table.querySelector('tbody');

    if (!thead || !tbody) {
        throw new Error('Table must have both thead and tbody');
    }

    // Apply widths to thead th elements
    const ths = thead.querySelectorAll('th');
    for (let i = 0; i < widths.length; i++) {
        ths[i].style.width = widths[i];
    }

    // Set padding for the last th element
    if (ths.length > 0) {
        const scrollbarWidth = thead.clientWidth - tbody.clientWidth;
        ths[ths.length - 1].style.setProperty('padding-right', `${scrollbarWidth}px`, 'important');
        ths[ths.length - 1].style.setProperty('width', '0px', 'important');
    }

    // Apply widths to tbody td elements
    const rows = tbody.querySelectorAll('tr');
    rows.forEach(row => {
        const tds = row.querySelectorAll('td');
        tds.forEach((td, index) => {
            if (widths[index]) {
                td.style.width = widths[index];
            }
        });
    });
}
