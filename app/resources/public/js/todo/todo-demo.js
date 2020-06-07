const TODO_ID_ATTR = 'data-todo-id'
const selectorIds = `input[${TODO_ID_ATTR}]`
const selectorChecked = `${selectorIds}:checked`

const selectedForm$ = $('#selectedForm')

const infoDialog$ = $('#infoDialog')

// don't submit if nothing selected
$('#showSelectedBtn').click(function (event) {
  const checkedItems$ = $(selectorChecked)
  if (checkedItems$.length < 1) {
    infoDialog$.modal('show')
    event.preventDefault()
  }
})

// Use Form post in SSR for a new view
// put the selected ids before submit
// append data a hidden field in form
selectedForm$.submit(function () {
  const checkedItems$ = $(selectorChecked)
  checkedItems$.each(function () {
    const id = $(this).attr(TODO_ID_ATTR)
    const newInput = $('<input>', {
      type: 'hidden',
      name: 'ids[]',
      value: id,
    })
    selectedForm$.append(newInput)
  })
})

$('#selectAllBtn').click(function () {
  $(selectorIds).each(function () {
    $(this).prop('checked', true)
  })
})

$('#unselectAllBtn').click(function () {
  $(selectorIds).each(function () {
    $(this).prop('checked', false)
  })
})
