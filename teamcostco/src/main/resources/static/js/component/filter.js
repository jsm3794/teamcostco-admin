const filter_apply = $('#filter_apply');
const filterOptions = $('#filter_options');
const filterBtn = $('#filter_btn');
let filter_list = [];

function addFilterList(filterDTO) {
    filter_list.push(filterDTO);
}

function clearFilterList() {
    filter_list = [];
}

function removeFilter(filterDTO) {
    const filterItems = $(`#${filterDTO.id}`);
    filterItems.val('');

    filter_list = filter_list.filter(filter => filter.id !== filterDTO.id);
    setFilter();
    displayFilterList();
}


function displayFilterList() {
    var container = $('#active-filters');
    container.empty(); // Clear existing content

    filter_list.forEach(filterDTO => {
        // Create a div for each filter
        var filterBlock = $('<div>', { class: 'filter-block' });

        // Create a span for the filter title and value
        console.log(filterDTO);
        var filterText = $('<span>', { class: 'filter-text' })
            .text(`${filterDTO.filter_title}: ${filterDTO.filter_innerText || filterDTO.value}`);

        // Create a button for removing the filter
        var removeButton = $('<button>', { class: 'remove-filter' })
            .text('X')
            .on('click', function () {
                removeFilter(filterDTO);
            });

        // Append the text and button to the filter block
        filterBlock.append(filterText).append(removeButton);

        // Append the filter block to the container
        container.append(filterBlock);
    });
}

filter_apply.on('click', (e) => {
    e.preventDefault();
    setFilter();
    toggleFilter();
});

function setFilter() {
    var target = $('#filter_items');

    clearFilterList();

    target.find('input, select').each(function () {
        var element = $(this);
        var value = element.val();
        if (value.trim() !== '') {
            // If value is not empty, set id as name
            element.attr('name', element.attr('id'));
            addFilterList(createFilterDTOFromElement(element));
        } else {
            // If value is empty, remove name attribute
            element.removeAttr('name');
        }
    });

    displayFilterList();
}


function addInputGroup(filterDTO) {
    var inputGroup = $('<div>', { class: 'input-group' });

    var label = $('<label>', { for: filterDTO.name }).text(filterDTO.filter_title);

    // Check if the filter is of type 'date'
    if (filterDTO.type === 'date') {
        // Create start date input
        var startDateElement = $('<input>', {
            type: 'date',
            id: filterDTO.name + '_start',
            value: getParamValue(filterDTO.name + '_start'),
            'data-title': filterDTO.filter_title + '(시작)'
        });

        // Create end date input
        var endDateElement = $('<input>', {
            type: 'date',
            id: filterDTO.name + '_end',
            value: getParamValue(filterDTO.name + '_end'),
            'data-title': filterDTO.filter_title + '(종료)'
        });

        var box1 = $('<div>', {class:'flex-row-box'});
        var box2 = $('<div>', {class:'flex-row-box'});

        inputGroup.append(label);
        box1.append($('<label>').text('시작일'));
        box1.append(startDateElement);
        box2.append($('<label>').text('종료일'));
        box2.append(endDateElement);

        inputGroup.append(box1);
        inputGroup.append(box2);

    } else {
        // Create a single input or select element for other types
        var element = $('<' + filterDTO.tag + '>', {
            type: filterDTO.type,
            id: filterDTO.name,
            value: getParamValue(filterDTO.name)
        });

        element.attr('data-title', filterDTO.filter_title);

        if (filterDTO.tag === 'select') {
            let dataArray = Array.from(filterDTO.data.entries());

            let sortedDataArray = dataArray.sort(([key1], [key2]) => {
                if (key1 === '') return -1;  // Make '' (전체) come first
                if (key2 === '') return 1;
                return 0;
            });

            sortedDataArray.forEach(([key, value]) => {
                var option = $('<option>', { value: key }).text(value);
                if (key === getParamValue(filterDTO.name)) {
                    option.attr('selected', 'selected');
                }
                element.append(option);
            });
        }

        inputGroup.append(label).append(element);
    }

    $('#filter_items').append(inputGroup);
}

function getParamValue(param) {
    var params = new URLSearchParams(window.location.search);
    var paramValue;
    params.forEach((value, key) => {
        if (key == param)
            paramValue = value;
    });
    return paramValue || '';
}



// 필터 버튼 클릭 시 동작 정의
filterBtn.on('click', () => {
    toggleFilter();
});

function toggleFilter() {
    filterOptions.toggleClass('hidden');
    filterBtn.toggleClass('hidden');
}



function fetchFilterData(filtertype) {
    return $.ajax({
        url: '/api/filter', // API endpoint
        method: 'GET',
        data: {
            filtertype: filtertype // Query parameter
        },
        dataType: 'json' // Expect JSON response
    });
}


function createFilterDTOFromElement(element) {
    let filter_innerText;
     // Check if the element is a 'select' element
     if (element.prop('tagName').toLowerCase() === 'select') {
        // Find the selected option and get its text
        filter_innerText = element.find('option:selected').text();
    } else {
        // For other elements, get the inner text directly
        filter_innerText = element.attr('innerText') || '';
    }

    return {
        id: element.attr('id'),
        name: element.attr('name'),
        value: element.val() || '10',
        filter_title: element.data('title') || '', // Or any other attribute you want to include
        tag: element.prop('tagName').toLowerCase(), // 'input' or 'select'
        filter_innerText: filter_innerText
    };
}


function createFilterDTO(filterTitle, tag, type, name, placeholder, data) {
    return {
        filter_title: filterTitle,
        tag: tag,
        type: type,
        name: name,
        placeholder: placeholder,
        data: new Map(Object.entries(data))  // Convert object to Map
    };
}

function initFilter(filterType) {

    fetchFilterData(filterType)
        .done(function (response) {

            // FilterDTO 객체 생성
            response.forEach(filter => {

                const filterDTO = createFilterDTO(
                    filter.filter_title || 'Default Title',
                    filter.tag || 'Default Tag',
                    filter.type || 'Default Type',
                    filter.name || 'Default Name',
                    filter.placeholder || 'Default Placeholder',
                    filter.data || {}
                );

                addInputGroup(filterDTO);

            });

            setFilter();

        })
        .fail(function (jqXHR, textStatus, errorThrown) {
            $('#result').text('Error: ' + textStatus + ' - ' + errorThrown);
        });
}

$(document).on('mousedown', function (event) {
    // 클릭된 요소가 필터 화면이나 필터 버튼이 아닌 경우 필터 화면을 숨깁니다.
    if (!filterOptions.is(event.target) && filterOptions.has(event.target).length === 0 &&
        !filterBtn.is(event.target) && filterBtn.has(event.target).length === 0) {
        filterOptions.addClass('hidden');
        filterBtn.addClass('hidden');
    }
});