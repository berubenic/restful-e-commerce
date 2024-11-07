const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      "/": {
        target: "http://localhost:8089",
        ws: false,
        changeOrigin: true,
      },
    },
  },
});
