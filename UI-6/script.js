function showField(nextFieldId, currFieldId){
    const currField = document.getElementById(currFieldId);
    const nextField = document.getElementById(nextFieldId);
    const input = currField.querySelector('input');

    if(input && input.value.trim() === ''){
        alert('Please fill out this field before proceeding.');
        return;
    }

    if(currField){
        currField.style.display = 'none';
    }

    if(nextField){
        nextField.style.display = 'block';
    }
}
