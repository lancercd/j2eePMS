## 注意模块划分

### 1.模块划分
属于那个模块请创建对应的文件夹

将对应模块的html文件放入对应模块

### 2.公共组件(重复的html代码)
将共同的html代码放入`**/templates/components/*.html`中

并写好备注


> `**/templates/`中尽量除了`index.html`其他的放到对应模块文件夹中


```html
**/templates/index.html
**/templates/*/*.html
```