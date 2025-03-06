功能概述
该红绿灯程序模拟交通信号灯的工作方式，包含红、黄、绿三种灯光，并按以下顺序变化：  
1. 红灯亮起 2 秒 
2. 黄灯慢速闪烁 4 次（间隔 1 秒）
3. 绿灯亮起 4 秒  
4. 所有灯熄灭，等待下一次点击  

 1.UI 布局设计
-使用 XML 文件 设计界面，包括 **3 个圆形视图**（红、黄、绿灯）和 1 个按钮（启动红绿灯）。利用 `View` 的背景颜色(`backgroundTintList`)** 来控制灯光变化。使用 `post {}` 让灯光保持圆形**，避免 `View` 变成方形。
2.事件处理
按钮点击事件 `setOnClickListener{}`触发红绿灯变化，并禁用按钮，防止重复点击。使用 `Handler.postDelayed()` 处理灯光切换的延时，模拟真实的红绿灯时序。
3.灯光逻辑
红灯亮起 2 秒，然后熄灭，进入黄灯阶段。
黄灯闪烁 4 次，每次 1 秒。
绿灯亮 4 秒，然后熄灭。
所有灯熄灭后，按钮重新启用。
4.保持圆形
`makeCircle(view: View)` 方法用于强制 `View` 保持圆形：取 `View` 的最小边长作为宽高，保证圆形比例。设置 `backgroundTintList` 让默认状态下灯光颜色为灰色。
5. 运行效果
- 点击按钮后，红灯亮 2 秒 → 黄灯慢速闪烁 4 次 → 绿灯亮 4 秒 → 所有灯熄灭 → 按钮恢复可用。整个过程完全自动化，无需额外操作，每次点击按钮即可重新启动红绿灯循环。
