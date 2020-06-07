# bs4-admin-template

An admin dashboard template using bootstrap 4, Vert.x, ZIO, and Quill.

## Run

Run `mill app.run` to run it in the project folder. Then access `localhost:8080` to see the home page.

The `localhost:8080/todos` use an embedded Postgres database for query.

Other pages are just demos of bootstrap componennts.

## Best practices

### Server Side Rendering (SSR)

It brings huge savings by not converting/marshalling data as the SPA does. The code is much simpler and quicker. The client side JS code only needs to do some simple form posting to interact with the backend. Except few cases, the REST JSON API is not used because pages are rendered in the server side.

### ScalaTags

It is a wonderful tool allows you to generate HTML pages using natvie Scala code. Very powerful and easy to learn.

### Build

- Separate build scripts.
- Use scalac compiler options and treat warnings as errors.

### Styles and JS

The admin template builds css style file `sb-admin-2.min.css` using bootstrap 4.4 scss files. The `bootstrap.bundle.min.js` include `Popper`, but not `jQuery. All libs are in the`public/lib` folder with version numbers.

For better performance, don't use outside images such as `<img class="rounded-circle" src="https://source.unsplash.com/fn_BT9fwg_E/60x60" alt="">` used in the top bar component.
