let display = document.querySelector("input[name='display']");
let operatorInput = document.querySelector("input[name='operator']");
let operatorSet = false;
let resultDisplayed = false;
let decimalAdded = false;

function addToDisplay(value) {
    if (resultDisplayed) {
        display.value = '';
        resultDisplayed = false;
    }

    if (operatorSet) {
        display.value += ' ' + value;
        operatorSet = false;
    } else {
        display.value += value;
    }
}

function setOperator(operator) {
    if (!resultDisplayed && !operatorSet) {
        operatorInput.value = operator;
        operatorSet = true;
        display.value += ' ' + operator;
        decimalAdded = false;
    }
}

function addDecimal() {
    if (!decimalAdded) {
        addToDisplay('.');
        decimalAdded = true;
    }
}

function resetState() {
    operatorSet = false;
    resultDisplayed = false;
    decimalAdded = false;
}

function handleSubmit(event) {
    if (operatorSet) {
        event.preventDefault();
        alert("Please input a valid expression.");
    }
    return !operatorSet;
}
// 음수 연산 활성화
document.addEventListener("DOMContentLoaded", function() {
    const negButton = document.querySelector("button[value='neg']");

    // 음수 버튼에 클릭 이벤트 리스너를 추가합니다.
    negButton.addEventListener("click", function(event) {
        // 기본 폼 제출 동작을 취소합니다.
        event.preventDefault();

        // 현재 화면의 값을 가져옵니다.
        const display = document.querySelector("#display");
        const currentValue = parseFloat(display.value);

        // 값이 숫자인지 확인하고 음수로 변환합니다.
        if (!isNaN(currentValue)) {
            display.value = -currentValue;
        }
    });
});