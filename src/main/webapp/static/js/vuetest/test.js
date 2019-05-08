// 这是我们的Model
var exampleData = {
    message: 'Hello World!'
}

// 创建一个 Vue 实例或 "ViewModel"
// 它连接 View 与 Model
var vue = new Vue({
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
        details: function () {
            return this.site + " - 学的不仅是技术，更是梦想！";
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
    data: {
        use: false,
        url: 'http://www.runoob.com'
    }
});

new Vue({
    el: '#app2',
    data: {
        ok: true,
        message: 'RUNOOB',
        id: 1
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
    methods: {
        add: function () {
            this.mess = this.mess + 'OO';
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
            return value + "mm";
        }
    }
})


new Vue({
    el: '#v-for',
    data: {
        sites: [
            {name: 'Runoob'},
            {name: 'Google'},
            {name: 'Taobao'},
            {address: 'add'}
        ],
        jsondata: [{
            id: 1,
            name: 'iphone 8',
            price: 5099,
            count: 1
        }, {
            id: 2,
            name: 'iphone xs',
            price: 8699,
            count: 1
        },
            {
                id: 3,
                name: 'iphone xr',
                price: 6499,
                count: 1
            }],
        object: {
            first: "A",
            second: "B",
            third: "C"
        },
        items: [
            {title: "Vue.js 教程", content: '学习vue.js，我们需要掌握···'},
            {title: "HTML 教程", content: 'HTML 是前端知识的入门基础···'}
        ]
    }
})


var app = new Vue({
    el: '#shopping',
    data: {
        Ip_Json: [{
            id: 1,
            name: 'iphone 8',
            price: 5099,
            count: 1
        },
            {
                id: 2,
                name: 'iphone xs',
                price: 8699,
                count: 1
            },
            {
                id: 3,
                name: 'iphone xr',
                price: 6499,
                count: 1
            }]

    },
    methods: {
        totalPrice: function () {
            var totalP = 0;
            for (var i = 0, len = this.Ip_Json.length; i < len; i++) {
                totalP += this.Ip_Json[i].price * this.Ip_Json[i].count;
            }
            return totalP;
        }


    }
})

var vm = new Vue({
    el: '#computed_props',
    data: {
        kilometers: 0,
        meters: 0
    },
    methods: {},
    computed: {},
    watch: {
        kilometers: function (val) {
            this.kilometers = val;
            this.meters = this.kilometers * 1000
        },
        meters: function (val) {
            this.kilometers = val / 1000;
            this.meters = val;
        }
    }
});

new Vue({
    el: '#activeColor',
    data: {
        activeColor: 'green',
        fontSize: 50
    }
});

new Vue({
    el: '#appForm',
    data: {
        checked: false,
        checkedNames: [],
        picked: 'xx',
        selected: ''
    }
});


// 注册
Vue.component('child', {
    // 声明 props
    props: ['message'],
    // 同样也可以在 vm 实例中像 “this.message” 这样使用
    //获取message属性的内容
    template: '<span>{{ message }}</span>'
})
// 创建根实例
new Vue({
    el: '#comm'
})

new Vue({
    el: '#parentMsg',
    data: {
        parentMsg: '父组件内容'
    }
})


Vue.component('todo-item', {
    props: ['todo'],
    template: '<li>{{ todo.text }}</li>'
})
new Vue({
    el: '#olli',
    data: {
        sites: [
            {text: 'Runoob'},
            {text: 'Google'},
            {text: 'Taobao'}
        ]
    }
})

/*自定义事件*/
Vue.component('example1', {
    props: ['value'],
    template: '<button v-on:click="incrementHandler">{{value}}</button>',
    methods: {
        incrementHandler: function () {
            this.$emit('newevert')
        }
    }
})
new Vue({
    el: '#div_col',
    data: {
        total: 0
    },
    methods: {
        incretmentTotal: function () {
            this.total += 1
        }
    }
})


/*自定义指令*/
Vue.directive('focus', {
    //第一次绑定时调用，常用于初始化操作
    bind: function () {

    },
    // 当绑定元素插入到 DOM 中。
    inserted: function (el) {
        console.info(el)
        // 聚焦元素
        el.focus()
    },
    //被绑定元素所在的模板更新时调用，而不论绑定值是否变化。通过比较更新前后的绑定值，可以忽略不必要的模板更新
    update: function () {

    },
    // 被绑定元素所在模板完成一次更新周期时调用。
    componentUpdated: function () {

    },
    //只调用一次， 指令与元素解绑时调用
    componentUpdated: function () {

    },
})
// 创建根实例
new Vue({
    el: '#app-focus'
})

new Vue({
    el: '#app-focus2',
    directives: {
        // 注册一个局部的自定义指令 v-focus
        focus: {
            // 指令的定义
            inserted: function (el) {
                // 聚焦元素
                el.focus()
            }
        }
    }
});


new Vue({
    el: '#vue-button',
    data: {
        navurl: 'static/directView/nav.html',
    }
});

new Vue({
    el: '#app-computed',
    data: {
        a: 1
    },
    computed: {
        b: function () {
            return this.a + 1
        }
    }

    //通过get方法获取属性和上面一样
    // computed: {
    //     b: {
    //         get:function () {
    //             return this.a + 1
    //         }
    //     }
    // }

});


new Vue({
    el: '#appwatch',
    data: {
        firstName: 'stephen',
        lastName: 'curry',
        fullName: 'stephen curry'
    },
    watch: {
        firstName: function (curVal, oldVal) {
            this.fullName = curVal + ' ' + this.lastName
        },
        lastName: function (curVal, oldVal) {
            this.fullName = this.firstName + ' ' + curVal
        }
    }
})


new Vue({
    el: '#appwatch2',
    data: {
        number: 1,
        result: 1
    },
    watch: {
        number: function (curVal, oldVal) {
            this.result = curVal * 1 + oldVal * 1
        }
    }
})


new Vue({
    el: '#appwatch3',
    data: {
        fruits: {
            name: "香蕉",
            count: 5
        },
        message: 'xxx'
    },
    watch: {
        fruits: {
            handler(obj) {
                this.message = obj.count + '条' + obj.name
            },
            deep: true
        }
    }
})


/*路由功能*/
// 0. 如果使用模块化机制编程，導入Vue和VueRouter，要调用 Vue.use(VueRouter)

// 1. 定义（路由）组件。
// 可以从其他文件 import 进来
const Foo = {template: '<div>foo</div>'}
const Bar = {template: '<div>bar</div>'}

// 2. 定义路由
// 每个路由应该映射一个组件。 其中"component" 可以是
// 通过 Vue.extend() 创建的组件构造器，
// 或者，只是一个组件配置对象。
// 我们晚点再讨论嵌套路由。
const routes = [
    {path: '/foo', component: Foo},
    {path: '/bar', component: Bar}
]

// 3. 创建 router 实例，然后传 `routes` 配置
// 你还可以传别的配置参数, 不过先这么简单着吧。
const router = new VueRouter({
    routes // （缩写）相当于 routes: routes
})

// 4. 创建和挂载根实例。
// 记得要通过 router 配置参数注入路由，
// 从而让整个应用都有路由功能
const appr = new Vue({
    router
}).$mount('#app-router')