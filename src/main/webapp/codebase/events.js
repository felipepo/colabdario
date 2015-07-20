/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var advancedEditor, authorship, basicEditor, cursorManager, _;

_ = Quill.require('lodash');

var nativeChange = true;

advancedEditor = new Quill('.advanced-wrapper .editor-container', {
    modules: {
        'authorship': {
            authorId: 'advanced',
            enabled: true
        },
        'toolbar': {
            container: '.advanced-wrapper .toolbar-container'
        },
        'link-tooltip': true,
        'image-tooltip': true,
        'multi-cursor': true
    },
    styles: false,
    theme: 'snow'
});

authorship = advancedEditor.getModule('authorship');

authorship.addAuthor('basic', 'rgba(255,153,51,0.4)');

cursorManager = advancedEditor.getModule('multi-cursor');

cursorManager.setCursor('basic', 0, 'basic', 'rgba(255,153,51,0.9)');

advancedEditor.on('selection-change', function (range) {
    return console.info('advanced', 'selection', range);
});

advancedEditor.on('text-change', function (delta, source) {
    var sourceDelta;
    if (source === 'api') {
        return;
    }
    console.info('advanced', 'text', delta, source);
    sendText(delta);
    nativeChange = true;
    sourceDelta = advancedEditor.getContents();
    return console.assert(_.isEqual(sourceDelta), "Editor diversion!", sourceDelta.ops);
});