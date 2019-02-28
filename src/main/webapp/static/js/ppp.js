
// (function ($) {
//     $.fn.lalala = function (options) {
//         alert(333)
//     }
// })


var Appp = function () {
    var index1 = function () {
        alert(1)
    }
    var index2 = function () {
        alert(2)
    }

    return {
        init4:function () {
            index1()
        },
        init2:function () {
          index1();
          index2()
        }
    }
}

Appp.init4;