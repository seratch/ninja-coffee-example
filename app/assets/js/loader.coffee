loadCoffeeSource = ->
  dfd = $.Deferred()
  $.ajax
    url:     '/assets/js/snippet.coffee'
    success: dfd.resolve
    error:   dfd.reject
  dfd.promise()

loadJsSource = ->
  dfd = $.Deferred()
  $.ajax
    url:      '/wro/snippet.js'
    dataType: 'text'
    success:  dfd.resolve
    error:    dfd.reject
  dfd.promise()

$.when(
  loadCoffeeSource(), loadJsSource()
).done(
  (cf, js) ->
    $('.coffee').html(cf[0])
    $('.js').html(js[0])
    prettyPrint()
)

