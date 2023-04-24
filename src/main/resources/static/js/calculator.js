const expressionElement = document.querySelector('#expression');

function handleSubmit(event) {
    event.preventDefault();
    const formattedExpressionInput = document.querySelector('input[name="formattedExpression"]');
    const formattedExpression = expressionElement.value
        .replace(/sin\(/g, "sin ")
        .replace(/cos\(/g, "cos ")
        .replace(/tan\(/g, "tan ")
        .replace(/abs\(/g, "abs ")
        .replace(/round\(/g, "round ")
        .replace(/log\(/g, "log ")
        .replace(/ln\(/g, "ln ")
        .replace(/\^/g, " ^ ")
        .replace(/\+/g, " + ")
        .replace(/-/g, " - ")
        .replace(/\*/g, " * ")
        .replace(/\//g, " / ")
        .replace(/\(/g, " ( ")
        .replace(/\)/g, " ) ");
    formattedExpressionInput.value = formattedExpression;
    event.target.submit();
}

function addToDisplay(value) {
    if (['sin(', 'cos(', 'tan(', 'abs(', 'round(', 'log(', 'ln('].some(op => expressionElement.value.endsWith(op))) {
        expressionElement.value += value;
    } else if (['sin', 'cos', 'tan', 'abs', 'round', 'log', 'ln'].includes(value)) {
        expressionElement.value += value + '(';
    } else {
        expressionElement.value += value;
    }
}


function setOperator(operator) {
    expressionElement.value += operator;
}

function addDecimal() {
    if (!expressionElement.value.includes('.')) {
        expressionElement.value += '.';
    }
}

function resetState() {
    const resultElement = document.querySelector('.result');
    expressionElement.value = '';
    resultElement.value = '';

}

function addToDisplayWithParentheses(value) {
    expressionElement.value += `${value}()`;
}

document.getElementById('delButton').addEventListener('click', () => {
    expressionElement.value = expressionElement.value.slice(0, -1);
});
// 이 연산들을 클릭시 ()와 함께 출력됩니다.
document.querySelectorAll('button[type="button"]').forEach(button => {
    const value = button.value;
    if (['sin', 'cos', 'tan', 'abs', 'round', 'log', 'ln'].includes(value)) {
        button.setAttribute('onclick', `addToDisplayWithParentheses('${value}')`);
    }
});
