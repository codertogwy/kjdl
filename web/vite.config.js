import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path';
import {fileURLToPath} from 'url'

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [vue()],
    server: {
        host: true,
        port: 80,
        proxy: {
            '/api': {
                target: 'http://127.0.0.1:9999',
                // target: 'http://192.144.236.154:9999',
                ws: true,
                changeOrigin: true,
                rewrite: (path) => path.replace(/^\/api/, ''),
            }
        }
    },
    resolve: {
        alias: {
            '@': path.resolve(path.dirname(fileURLToPath(import.meta.url)), "src"),
        }
    }
})
