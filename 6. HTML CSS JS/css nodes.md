# 1. 什麼是CSS

> Cascading style sheet
>
> -> **美化網頁**，字體，顏色，邊距，高度，背景等等



# 2. css三種導入方法

![image-20210117223644072](css nodes.assets/image-20210117223644072.png)

> 留意如果有多於一種style作用係一樣野上，係會用比較近果個，因為會覆蓋



# 3. 選擇器 selector

## 3.1 basic seletor

> 1. label selector
> 2. class selector
> 3. id selector
>
> 留意呢度唔係就近原則，而係有priority
>
> id > class > label，例如label同id都作用係同一個野，id會贏

```html
<!DOCTYPE html>
<html>
<head>
	<title>selector</title>
	<style type="text/css">
		/* 1. label selector，會選擇所有該label*/
		h1 {
			color: red;
		}
		/* 2. class selector，只會選擇該class name，可以重複用同一class name，*/
		.class1{
			color: blue;
		}
		/* 3. id selector，用# ，只會選擇該id，id只能有一個*/
		#id1{
			color: pink;
		}
	</style>
</head>
<body>
	<h1>h1 title 1</h1>
	<h1 class="class1">h1 title 2</h1>
	<h1 id="id1">h1 title 3</h1>

</body>
</html>
```



## 3.2 layer selector

> 1. 後代選擇器
> 2. 子選擇器
> 3. 弟弟選擇器
> 4. 所有弟弟選擇器

```html
<!DOCTYPE html>
<html>
<head>
	<title>layer selector</title>

	<style type="text/css">
		/*1. 後代選擇器
			元素下面所有該項label全被選中
			例如呢度係揀曬所有p (p1-p6)
		*/
		body p{
			color: red;
		}
		/*2. 子選擇器
			只選一代，只選一代，只選一代
			例如呢度係只選p1-p3
		*/
		body>p{
			color: pink;
		}

		/*3. 兄弟(弟弟)選擇器
			只睇下面一個兄弟，如果符合條件就揀佢 ******上面係唔睇
			例如呢度揀嘅係p2下面嘅兄弟，所以係p3，p1唔關事
		*/
		.class1 + p{
			background-color: yellow;
		}

		/*4. 通用(所有弟弟)選擇器
			選擇同級樓下所有兄弟
			例如呢度會揀p3,p7,p8
		*/
		.class~p{
			background-color: red;
		}

	</style>
</head>
<body>
	<p>p1</p>
	<p class="class1">p2</p>
	<p>p3</p>
	<ul>
		<li>
			<p>p4</p>
		</li>
		<li>
			<p>p5</p>
		</li>
		<li>
			<p>p6</p>
		</li>
	</ul>
	<p>p7</p>
	<p>p8</p>
</body>
</html>
```

