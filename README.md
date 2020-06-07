# bs4-admin-template

An admin dashboard template using bootstrap 4, Vert.x, ZIO, and Quill.

## Build

- Separate build scripts.
- Use scalac compiler options and treat warnings as errors.

## Styles and JS

The admin template builds css style file `sb-admin-2.min.css` using bootstrap 4.4 scss files. The `bootstrap.bundle.min.js` include `Popper`, but not `jQuery. All libs are in the`public/lib` folder with version numbers.

For better performance, don't use outside images such as `<img class="rounded-circle" src="https://source.unsplash.com/fn_BT9fwg_E/60x60" alt="">` used in the top bar component.
