import {createRouter, createWebHashHistory} from 'vue-router';
import BasicLayout from '@/layout/BasicLayout.vue'
import BlankLayout from '@/layout/BlankLayout.vue'
import {getToken} from "@/utils/session.js";

const routes = [
    {
        path: '/',
        component: BasicLayout,
        redirect: '/home',
        children: [
            {
                path: '/home',
                name: 'Home',
                meta: {title: '主页', icon: 'House'},
                component: () => import('@/views/system/home.vue'),
            },
            {
                path: '/basic',
                name: 'Basic',
                meta: {title: '基础资料', icon: 'Watermelon'},
                component: BlankLayout,
                redirect: 'Material',
                children: [
                    {
                        path: '/basic/material',
                        name: 'Material',
                        meta: {title: '物资列表', icon: 'Pear'},
                        component: () => import('@/views/basic/Material.vue'),
                    },
                ],
            },
            {
                path: '/purchase',
                name: 'Purchase',
                meta: {title: '采购', icon: 'Watermelon'},
                component: BlankLayout,
                redirect: 'Supplier',
                children: [
                    {
                        path: '/purchase/supplier',
                        name: 'Supplier',
                        meta: {title: '供应商', icon: 'Pear'},
                        component: () => import('@/views/purchase/Supplier.vue'),
                    },
                    {
                        path: '/purchase/inquiry',
                        name: 'Inquiry',
                        meta: {title: '询价', icon: 'Pear'},
                        component: () => import('@/views/purchase/Inquiry.vue'),
                    },
                    {
                        path: '/purchase/purchaseContract',
                        name: 'PurchaseContract',
                        meta: {title: '采购合同', icon: 'Pear'},
                        component: () => import('@/views/purchase/PurchaseContract.vue'),
                    },
                ],
            },
            {
                path: '/sale',
                name: 'Sale',
                meta: {title: '销售', icon: 'Watermelon'},
                component: BlankLayout,
                redirect: 'Customer',
                children: [
                    {
                        path: '/sale/customer',
                        name: 'Customer',
                        meta: {title: '客户', icon: 'Pear'},
                        component: () => import('@/views/sale/Customer.vue'),
                    },
                    {
                        path: '/sale/project',
                        name: 'Project',
                        meta: {title: '项目', icon: 'Pear'},
                        component: () => import('@/views/sale/Project.vue'),
                    },
                    {
                        path: '/sale/salesContract',
                        name: 'SalesContract',
                        meta: {title: '销售合同', icon: 'Pear'},
                        component: () => import('@/views/sale/SaleContract.vue'),
                    },
                ],
            },


            // {
            //     path: '/circulation',
            //     name: 'circulation',
            //     meta: {title: '流转', icon: 'Watermelon'},
            //     component: BlankLayout,
            //     redirect: 'delivery',
            //     children: [
            //         {
            //             path: '/circulation/delivery',
            //             name: 'delivery',
            //             meta: {title: '发货', icon: 'Pear'},
            //             component: () => import('@/views/circulation/Delivery.vue'),
            //         },
            //         {
            //             path: '/circulation/stock',
            //             name: 'stock',
            //             meta: {title: '库存', icon: 'Pear'},
            //             component: () => import('@/views/circulation/Stock.vue'),
            //         },
            //         {
            //             path: '/circulation/outbound',
            //             name: 'outbound',
            //             meta: {title: '等待出库', icon: 'Pear'},
            //             component: () => import('@/views/circulation/Outbound.vue'),
            //         },
            //     ],
            // },
            {
                path: '/test',
                name: 'Test',
                meta: {title: 'test', icon: 'House'},
                component: () => import('@/views/Test.vue'),
            },
        ],
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('@/views/system/Login.vue'),
    },
    {
        path: "/:any(.*)",
        name: "404",
        component: () => import("@/views/system/404.vue")
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

const white = [
    '/login'
]

router.beforeEach((to, from, next) => {
    if (white.indexOf(to.path) === -1 && !getToken()) {
        next('/login')
    } else next()
})


export default router