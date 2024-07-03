<img src="G:\Downloads\Screenshot_20240610_224449_com.example.androidstu.jpg" alt="Screenshot_20240610_224449_com.example.androidstu" style="zoom:25%;" />

<img src="G:\Downloads\Screenshot_20240610_224534_com.example.androidstu.jpg" alt="Screenshot_20240610_224534_com.example.androidstu" style="zoom:25%;" />![Screenshot_20240610_232156_com.example.androidstu](G:\Downloads\Screenshot_20240610_232156_com.example.androidstu.jpg)

![Screenshot_20240610_232156_com.example.androidstu](G:\Downloads\Screenshot_20240610_232156_com.example.androidstu.jpg)





在NetworkActivity中，存在以下泄露内存的问题：

1. Handler泄露：在Activity中创建的Handler对象是一个匿名内部类，它持有对Activity的隐式引用。如果在Activity被销毁之前，Handler仍然保持活动状态，它将导致Activity无法被垃圾回收，从而造成内存泄漏。为了解决这个问题，可以使用静态内部类来创建Handler，并将其与Activity的弱引用关联起来。
2. OkHttp Call泄露：在getWithCallback()方法中，将OkHttp的Call对象添加到请求队列中，但没有在适当的时候取消请求。如果Activity被销毁或者用户取消了请求，Call对象仍然保持活动状态，从而导致内存泄漏。为了解决这个问题，可以在Activity的onDestroy()方法中取消所有未完成的请求。

通过使用弱引用来创建Handler，并在Activity销毁时取消请求，可以避免泄漏内存的问题。



<img src="G:\Downloads\Screenshot_20240610_224542_com.example.androidstu.jpg" alt="Screenshot_20240610_224542_com.example.androidstu" style="zoom:25%;" />



在PermissionActivity中，存在以下内存泄露的风险：

1. 在onCreate方法中，将当前Activity对象添加到静态List<Activity>变量leaks中，但没有在Activity销毁时将其从leaks中移除。这可能导致Activity无法被垃圾回收，从而造成内存泄露。为了解决这个问题，可以在Activity的onDestroy方法中将当前Activity对象从leaks中移除。
2. 在scanSd方法中，通过递归方式遍历文件夹并显示文件信息，但没有在遍历完成后释放相关资源。这可能导致文件句柄未关闭，从而造成内存泄露。为了解决问题，可以在遍历完成后调用File的close方法关闭文件句柄。
3. 在startCamera方法中，通过Intent启动相机应用，但没有在相机应用返回结果后处理结果。这可能导致Activity无法被销毁，从而造成内存泄露。为了解决问题，可以在onActivityResult方法中处理相机应用返回的结果，并在处理完成后及时释放相关资源。