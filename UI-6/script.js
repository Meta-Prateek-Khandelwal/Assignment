function showField(nextFieldId, prevFieldId){
    const prevField = document.getElementById(prevFieldId);
    if(prevField){
        prevField.style.display = 'none';
    }

    const nextField = document.getElementById(nextFieldId);
    if(nextField){
        nextField.style.display = 'block';
    }
}
