// entry.js
require('!style-loader!css-loader!./style.css');

var hello = require('./hello');
var world = require('./world');

function test(){
    return hello + ', ' + world + '!';
}

document.write(test());