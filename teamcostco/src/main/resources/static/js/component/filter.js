/**
 * 입력 그룹을 동적으로 추가하는 함수
 * @param {string} type - 입력 요소의 타입 ('text', 'checkbox', 'date', 'select' 등)
 * @param {string} title - label에 표시될 제목
 * @param {string} name - 생성될 입력 요소의 name 속성
 * @param {string} id - 생성될 입력 요소의 id 속성
 * @param {Array<{name: string, text: string}>} [options] - select 요소의 경우, 목록으로 표시할 옵션 (name 및 text)
 * @param {string} [formId] - 요소를 추가할 form의 ID (값이 없으면 filter_options에 추가)
 */
function addInputGroup(type, title, name, id, options = [], formId = null) {
    // 입력 그룹을 담을 div 생성
    var inputGroup = $('<div>', { class: 'input-group' });

    // label 생성
    var label = $('<label>').text(title + ': ');

    let input;

    switch (type) {
        case 'select':
            input = $('<select>', { name: name, id: id });
            options.forEach(option => {
                $('<option>', { value: option.name }).text(option.text).appendTo(input);
            });
            break;
        case 'checkbox':
            input = $('<input>', { type: 'checkbox', name: name, id: id });
            break;
        case 'date':
            input = $('<input>', { type: 'date', name: name, id: id });
            break;
        default:
            input = $('<input>', { type: type || 'text', name: name, id: id, placeholder: 'Enter ' + title });
    }

    // inputGroup에 label과 input 추가
    inputGroup.append(label).append(input);

    // formId가 주어진 경우, 해당 form에 추가
    if (formId) {
        $('#' + formId).append(inputGroup);
    } else {
        // formId가 없으면, 기본 div에 추가 (예: filterOptions)
        $('#filter_items').append(inputGroup);
    }
}

// 예시: 다양한 타입의 input 요소 추가
addInputGroup('text', 'Name', 'name', 'name');
addInputGroup('checkbox', 'Subscribe', 'subscribe', 'subscribe');
addInputGroup('date', 'Date of Birth', 'dob', 'dob');
addInputGroup('select', 'Country', 'country', 'country', [
    { name: 'us', text: 'United States' },
    { name: 'ca', text: 'Canada' },
    { name: 'mx', text: 'Mexico' }
]);

var filterOptions = $('#filter_options');
var filterBtn = $('#filter_btn');

// 필터 버튼 클릭 시 동작 정의
filterBtn.on('click', () => {
    filterOptions.toggleClass('hidden');
    filterBtn.toggleClass('hidden');
});
