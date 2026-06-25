const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  transpileDependencies: true,
  // devServer: {
  //   client: {
  //     overlay: false,
  //   },
  // },
  chainWebpack: config => {
    config.plugin('define').tap(definitions => {
      Object.assign(definitions[0]['process.env'], {
        NODE_HOST: '"http://localhost:9999"',
      });
      return definitions;
    });
  }
})
