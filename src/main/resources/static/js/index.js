$(document).ready(function () {

    $('form').submit(function (event) {
        event.preventDefault()
        var $button = $(this).find('button[type=submit]').addClass('active').prop('disabled', true)
        var $select = $(this).find('select').prop('disabled', true)
        $.ajax({
            url: '/index?cityName=' + $('#city').val(),
            method: 'POST',
            contentType: 'application/json'
        }).always(function () {
            $button.removeClass('active').prop('disabled', false)
            $select.prop('disabled', false)
        })
    })

})