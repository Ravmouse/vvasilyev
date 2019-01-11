function validate() {
    var rsl = true;
    var elements = document.querySelectorAll('input, textarea');
    for (var i = 0; i < elements.length; i++) {
        if (elements[i].value === "") {
            rsl = false;
            alert('The next field is empty: ' + $(elements[i]).attr('name'));
            break;
        }
    }
    return rsl;
}

function load(obj) {                                        //Сюда передается комбобокс-элемент (select)
    if (obj.selectedIndex)                                  //и для выбранного значения (страны)
        var country = obj.options[obj.selectedIndex].text;  //получается текст этого выбранного значения
    getCities(country);                                     //для того, чтобы передать в эту ф-ю
}

function getCities(obj) {
    $.ajax({
        url: './cc',
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            $('#city').empty();
            $('#city').append('<option value=\"\" disabled selected>--Select city--</option>');
            var arrays = data.countries;                    //Получаются значения JSON по ключу countries - массив JSON'ов
            $.each(arrays, function (k, v) {                //для каждого элемента массива
                if (v.name === obj) {                       //если значение по ключу name равно названию страны, переданной в ф-ю
                    $.each(v.cities, function (key, val) {  //то каждое значение массива по ключу cities
                        var opt_data = "<option value=\"" + val +"\">" + val +  "</option>";
                        $(opt_data).appendTo('#city');      //добавляется в элемент с id = city
                    })
                }
            })
        }
    })
}