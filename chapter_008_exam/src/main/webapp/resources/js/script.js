function validate() {
    var rsl = true;
    var elements = document.querySelectorAll('input, textarea');
    for (var i = 0; i < elements.length; i++) {
        if (elements[i].value === "") {
            rsl = false;
            alert('Вы не заполнили поле: ' + $(elements[i]).attr('name'));
            break;
        }
    }
    return rsl;
}