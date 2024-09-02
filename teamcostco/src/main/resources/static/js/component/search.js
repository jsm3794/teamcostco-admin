const filter_btn = $('#search_btn');
const searchInput = $('#search_input');
const formId = searchInput.attr('for');
const form = $('#' + formId)[0];
const listSizeInput = $('#list_size');

filter_btn.on('click', (e) => {
    if (form.checkValidity()) {
        form.submit();
    } else {
        form.reportValidity();
    }
});


searchInput.on('keypress', (event) => {
    if (event.key === 'Enter') {
        event.preventDefault();
        form.submit();
    }
});


listSizeInput.on('keypress', (event) => {
    if (event.key === 'Enter') {
        event.preventDefault();
        form.submit();
    }
});

