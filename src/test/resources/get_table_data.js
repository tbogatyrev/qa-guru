jsonObject = {};
$('.table-responsive tbody tr').each(function (i, element) {
    let key = $(element).find('td').first().text();
    let value = $(element).find('td').last().text();
    jsonObject[key] = value;
});
return JSON.stringify(jsonObject);