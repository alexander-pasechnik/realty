$(document).ready(function () {
    $('[data-toggle="popover"]').popover({
        delay: { "show": 500, "hide": 100 },
        placement: 'bottom',
        container: 'body',
        trigger: 'hover',
        viewport: 'table'
    })
})
