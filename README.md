# todo-list

* 这个项目是我的`Java`面向对象程序设计课程的期末大作业  
* 使用`JavaFX`开发  

<div align="center">
<img src="https://fox-blog-image-1312870245.cos.ap-guangzhou.myqcloud.com/202212292203764.jpg" width="50%"/>
</div>


## 简要介绍  

这是一个“待办事项清单”程序，整个窗口由左边的日历和右边的代办事项列表组成。  

### 日历使用介绍  

1. 顶部的左右两个按钮可以实现**上一个月**和**下一个月**的切换。
2. 当前日期(今天)的位置会高亮。
3. 点击日期单位可以**选中**该日期，有一个边框作为标记。  

### 待办事项列表使用介绍

1. 顶部是当前选中的日期，**每一个日期对应一个待办事项列表**。  
2. 列表的主体部分由若干个待办事项组成，每个待办事项可以点击`done`按钮表示**完成**，点击`delete`按钮表示**删除**该待办事项。  
3. 底部是一个输入框和一个添加按钮，在输入框输入待办事项，点击添加按钮，就可以将待办事项添加到选中的日期中。  

## 项目结构

使用组件化，类似于`HTML`的大盒子包含小盒子。  
组件结构如图：  

<img src="https://fox-blog-image-1312870245.cos.ap-guangzhou.myqcloud.com/202212292319984.jpg" width="100%"/>


待办事项数据结构如图: 

<div align="center">
<img src="https://fox-blog-image-1312870245.cos.ap-guangzhou.myqcloud.com/202212292312151.jpg" width="100%"/>
</div>

数据会被保存到`list_map.dat`文件里.  
