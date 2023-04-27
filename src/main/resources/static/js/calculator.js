const expressionElement = document.querySelector('#expression');
        // .replace(/sin\(/g, "sin ")
        // .replace(/cos\(/g, "cos ")
        // .replace(/tan\(/g, "tan ")
        // .replace(/abs\(/g, "abs ")
        // .replace(/round\(/g, "round ")
        // .replace(/log\(/g, "log ")
        // .replace(/ln\(/g, "ln ")

async function handleSubmit(event) {
    // event.preventDefault();

    const formattedExpression = expressionElement.value
        .replace(/\^/g, " ^ ")
        .replace(/\+/g, " + ")
        .replace(/-/g, " - ")
        .replace(/\*/g, " * ")
        .replace(/\//g, " / ")
        .replace(/\(/g, " ( ")
        .replace(/\)/g, " ) ");
    console.log('Formatted expression:', formattedExpression);

    const requestData = {
        expression: formattedExpression
    };
    console.log('Request data:', requestData);

    const response = await fetch('/calculate', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestData),
    });

    if (response.ok) {
        const data = await response.json();
        if (data.error) {
            console.error('Calculation error:', data.error);
            document.querySelector('#result').value = "Error";
        } else {
            document.querySelector('#result').value = data.result !== undefined ? data.result : "Error";
        }
    } else {
        console.error('Failed to calculate:', response.status, response.statusText);
    }
}



function addToDisplay(value) {
    if (['(', ')', 'sin(', 'cos(', 'tan(', 'abs(', 'round(', 'log(', 'ln('].some(op => expressionElement.value.endsWith(op))) {
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
    const resultElement = document.querySelector('#result');
    expressionElement.value = '';
    resultElement.value = '';
    location.href = "/";
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
// 페이지의 DOM이 완전히 로드된 후 실행됨
document.addEventListener('DOMContentLoaded', () => {
    document.querySelector('.calculator').addEventListener('submit', handleSubmit);
});

