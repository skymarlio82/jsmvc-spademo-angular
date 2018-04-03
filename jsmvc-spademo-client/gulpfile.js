
var gulp = require('gulp');
var cleanCSS = require('gulp-clean-css');
var uglify = require('gulp-uglify');

var paths = {
    "css"    : "src/css/*",
    "images" : "src/images/*",
    "htm"    : "src/fragment/*.htm",
    "js"     : "src/js/**/*.js",
    "dist"   : "target/dist/"
};

gulp.task('minify-css', function () {
    return gulp.src(paths.css).pipe(cleanCSS()).pipe(gulp.dest(paths.dist + 'css/'));
});

gulp.task('minify-js', function () {
    return gulp.src(paths.js).pipe(uglify()).pipe(gulp.dest(paths.dist + 'js/'));
});

gulp.task('copy-htm', function () {
    return gulp.src(paths.htm).pipe(gulp.dest(paths.dist + 'fragment/'))
});

gulp.task('copy-images', function () {
    return gulp.src(paths.images).pipe(gulp.dest(paths.dist + 'images/'))
});

gulp.task('default', ['minify-css', 'minify-js', 'copy-htm', 'copy-images'], function () {});
