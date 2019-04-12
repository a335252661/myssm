// 这是我们的Model
var exampleData = {
    message: 'Hello World!'
}

// 创建一个 Vue 实例或 "ViewModel"
// 它连接 View 与 Model
var vue  = new Vue({
    el: '#app',
    data: exampleData
})

// console.info(vm.$data === data)
document.write(vue.$data === exampleData) // true
document.write(vue.message === exampleData.message) // true



var vm = new Vue({
    el: '#vue_det',
    data: {
        site: "菜鸟教程",
        url: "www.runoob.com",
        alexa: "10000"
    },
    methods: {
        details: function() {
            return  this.site + " - 学的不仅是技术，更是梦想！";
        }
    }
})

/*v-html*/
new Vue({
    el: '#v-html',
    data: {
        mydata: '<h1>vue-html</h1>'
    }
})

/*v-bind*/
new Vue({
    el: '#v-bind',
    data:{
        use: false,
        url:'http://www.runoob.com'
    }
});

new Vue({
    el: '#app2',
    data: {
        ok: true,
        message: 'RUNOOB',
        id : 1
    }
})

new Vue({
    el: '#v-if',
    data: {
        seen: true
    }
})


new Vue({
    el: '#v-on',
    data: {
        mess: "qwer"
    },
    methods:{
        add:function () {
            this.mess = this.mess+'OO';
        }
    }
})


new Vue({
    el: '#filters',
    data: {
        address: 'xiaobudain'
    },
    filters: {
        touppcase: function (value) {
            if (!value) return ''
            value = value.toString()
            return value.toUpperCase()
        },
        add: function (value) {
            return value+"mm";
        }
    }
})


new Vue({
    el: '#v-for',
    data: {
        sites: [
            { name: 'Runoob' },
            { name: 'Google' },
            { name: 'Taobao' },
            { address: 'add' }
        ]
    }
})