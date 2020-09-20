# 本README包含以下内容
1. 代码示例
2. 概念说明
3. 主要类及函数说明
4. 生成系统
# 代码示例
*  请参阅github的example文件夹
# 概念说明
## 生成位点及生成接口位点
![1](https://pic.downk.cc/item/5f6745e6160a154a67fc2f12.png)
> * 生成位点，即JigsawSummonNode。  
> * 生成接口位点，即JigsawSummonNodeSocket。  
> * 如果说生成位点是一块拼图的凸，那么生成接口位点就是一块拼图的凹。  
> * 他们被用于生成的启动，即生成位点。
## 匹配位点与非匹配位点
![2](https://pic.downk.cc/item/5f6745e6160a154a67fc2f19.png)
> * 并不是任意生成位点都能适合生成接口位点。  
> * 类型相同的生成位点和生成接口位点拼接位点称之为匹配位点。  
> * 类型相同的生成位点和生成接口位点拼接位点称之为非匹配位点。  
> * 非匹配位点会在生成时被生成器排除。
## 拼图样板
> *  即JigsawPattern，是一个存放单元及接口信息的容器。
## 拼图块
> * 每一个JigsawPiece被称为一个拼图块。
## 多单元
![3](https://pic.downk.cc/item/5f6745e6160a154a67fc2f1b.png)
> * 拼图样板可以存放一个拼图块，也可以存放多个拼图块。  
> * 通过多单元组合可以形成不同形状的拼图样板
## 多单元的位点冲突
![4](https://pic.downk.cc/item/5f6745e6160a154a67fc2f1e.png)
> * 在拼接过程中，即使形成匹配位点，但在实际情况上有时会与已存在的拼图块产生冲突，称之为位点冲突。
## 拼接过程
> 1. 以一个生成位点为起点，尝试匹配所有可行的生成接口位点。
> 2. 剔除所有具有位点冲突的尝试。
> 3. 使用BFS/DFS经行迭代生成。
# 主要类及函数说明
## Jigsaw
> `Jigsaw`是一个包装好的类。
> |前缀|返回值|函数名称|参数|功能及说明|
> |  ----  | ----  |  ----  | ----  | ----  |
> |Constructor|||JigsawStructureGenerator||	
> |public abstract|	BlockPos|	getActualPos|	JigsawSectionPos jigsawSectionPos, int x, int y, int z	|设置JigsawSectionPos向BlockPos转换关系|
> |public <V extends IJigsawPattern>|	List<StructurePiece>|	getComponents|	int x, int y, int z, Supplier<V> beginPattern, TemplateManager templateManager|	获得生成的StructurePiece列表|

## JigsawStructureGenerator
>JigsawStructureGenerator是负责结构生成的具体负责组件。
>|前缀|返回值|函数名称|参数|功能及说明|
>|  ----  | ----  |  ----  | ----  | ----  |
>|Constructor|||			JigsawPool jigsawPool||	
>|Constructor||||			
>|public|	JigsawStructureGenerator(this)|	setWeightRandomFunction|	Function<LinkedList<WeightRandomItem<Pair<JigsawSectionPos, IJigsawPattern>>>,Pair<JigsawSectionPos, IJigsawPattern>> weightRandomFunction|	设置获得通过加权随机获得JigsawPattern的方法|
>|public|	JigsawStructureGenerator(this)|	setTerminatorChecker|	boolean terminatorChecker|	设置是否使用终结点功能|
>|public|	JigsawStructureGenerator(this)|	setMode|	Mode mode|	设置启用模式，分为BFS和DFS两种，推荐使用BFS|
>|public|	JigsawStructureGenerator(this)|	setJigsawPool|	JigsawPool jigsawPool|	设置JigsawPattern池，即JigsawPool|
>|public|	JigsawStructureGenerator(this)|	setLimit|	Integer limit	|设置BFS的生成总数限制或DFS的深度限制|
>|public <T extends IJigsawPattern>|	void|	generate|	JigsawSectionPos startPos, T startPattern|	开始生成，并返回生成数据|

## JigsawPool
>JigsawPool是存放所有JigsawPattern的池。
>|前缀|	返回值|	函数名称|	参数|	功能及说明|
>|  ----  | ----  |  ----  | ----  | ----  |
>|Constructor|	|||			默认构造函数|
>|Constructor|			||Supplier<? extends IJigsawPattern>... suppliers|	通过构造函数添加JigsawPattern|
>|public|	void|	register|	Supplier<? extends IJigsawPattern> supplier|	单独添加JigsawPattern|
>|public|	void|	init||		初始化并生成reflectJMSP|
>|public|	Map<JigsawSummonNode, HashSet<JigsawSummonNodeSocket>>|	getReflectJMSP||		获得reflectJMSP|

## IJigsawPattern
>IJigsawPattern即为JigsawPattern，是本模组的主要容器
>|返回值|函数名称|参数|功能及说明|
>|----|----|----|----|
>|JigsawOccupiedSectionPool|	getOccupiedSectionPool||		获得占用空间信息|
>|JigsawSummonNodesPool|	getSummonNodes||		获得生成节点信息|
>|JigsawSummonNodeSocketPool|	getSummonNodeSocketPool	||	获得生成节点接口信息|
>|JigsawPatternType	|getJigsawPatternType||		获得JigsawPattern类型|

## JigsawPiece
>JigsawPiece是JigsawPattern的重要组成，该类为抽象类
>|前缀|	返回值|	函数名称|	参数|	功能及说明|
>|  ----  | ----  |  ----  | ----  | ----  |
>|protected abstract|	ResourceLocation|	getResourceLocation||		获得nbt结构文件的位置|
>|protected abstract|	boolean|	isIgnoreEntities||		设置是否选择忽视实体生成|
>|protected abstract|	boolean|	isVirtual||		设置是否为虚态占用，即不占用当前位置的空间|

## JigsawSummonNode
>JigsawSummonNode为生成位点
>|前缀|	返回值|	函数名称|	参数|	功能及说明|
>|  ----  | ----  |  ----  | ----  | ------|
>|Constructor|||JigsawSectionPos nodeSectionPos, SimpleDirection simpleDirection,String jigsawSummonNodeType|	设置信息的构造函数|
>|public|	JigsawSummonNode(this)|	setTerminator|	boolean terminator|	设置该生成节点是否为终结点|
>|public|	boolean|	isTerminator||		返回是否为终结点|
>|public|	JigsawSectionPos|	getNodeSectionPos	||	获得生成节点位置信息|
>|public|	SimpleDirection|	getSimpleDirection||		获得生成节点方向信息|
>|public|	JigsawSummonNodeSocketType|	getJigsawSummonNodeType	||	获得生成节点类型|

## JigsawSummonNodeSocket
>JigsawSummonNodeSocket为生成节点接口
>|前缀|	返回值|	函数名称|	参数|	功能及说明|
>|  ----  | ----  |  ----  | ----  | ----  |
>|Constructor||			|JigsawSectionPos socketSectionPos, SimpleDirection simpleDirection, String type, int weight, Supplier<IJigsawPattern> jigsawPattern|	设置信息的构造函数|
>|public|	JigsawSummonNodeSocket(this)|	setIgnoreOccupation|	boolean terminator|	设置该生成节点是否忽视位置占用|
>|public|	boolean|	isIgnoreOccupation	||	返回是否忽视位置占用|
>|public|	JigsawSectionPos|	getSocketSectionPos||		获得生成节点位置信息|
>|public|	SimpleDirection|	getSimpleDirection	||	获得生成节点方向信息|
>|public|	JigsawSummonNodeSocketType|	getJigsawSummonNodeType	||	获得生成节点类型|
>|public|	IJigsawPattern|	getJigsawPattern||		获得自身处在的JigsawPattern|

## JigsawSide
>JigsawSide为侧边信息
>|前缀|	返回值|	函数名称|	参数|	功能及说明|
>|  ----  | ----  |  ----  | ----  | ----  |
>|Constructor||	||			默认构造函数|
>|public|	JigsawSide(this)|	east|	JigsawSideType jigsawSideType|	设置east方向的侧边信息|
>|public|	JigsawSide(this)|	south|	JigsawSideType jigsawSideType|	设置south方向的侧边信息|
>|public|	JigsawSide(this)|	west|	JigsawSideType jigsawSideType|	设置west方向的侧边信息|
>|public|	JigsawSide(this)|	north|	JigsawSideType jigsawSideType|	设置north方向的侧边信息|
>|public|	JigsawSide(this)|	up|	JigsawSideType jigsawSideType|	设置up方向的侧边信息|
>|public|	JigsawSide(this)|	down|	JigsawSideType jigsawSideType|	设置down方向的侧边信息|

## JigsawSideType
>JigsawSideType 为侧边类型信息
>|前缀|	返回值|	函数名称|	参数|	功能及说明|
>|  ----  | ----  |  ----  | ----  | ----  |
>|Constructor|||			String type|	设置侧边类型的构造函数|
>|Constructor|||		String type, String... validTypes|	设置侧边类型及有效类型的构造函数|
>|public|	String|	getType||		获得侧边类型|
>|public|	HashSet<JigsawSideType>|	getValidSideTypes||		获得有效侧边类型|
## JigsawMapState
>JigsawMapState
>|前缀|	返回值|	函数名称|	参数|	功能及说明|
>|  ----  | ----  |  ----  | ----  | ----  |
>|public|	void|	register|	JigsawSectionPos jigsawSectionPos, IJigsawPattern jigsawPattern|	将JigsawPiece注册到内置地图上|


# 组件创建
> 1.JigsawPattern的创建可参考jigsawPattern code template.txt中的内容。  
> 2.JigsawPiece的创建可参考jigsawPiece_with_side code template.txt中的内容。

# 生成系统
## 生成示例
```
JigsawFactorPool jigsawFactorPool = new JigsawFactorPool();
//如需启用因子化生成，请加上上面这句
jigsawFactorPool.register(A_Jigsaw_Pattern::getInstance, new DefaultLimitedFactorItem(1, 0.5f));
//因子化生成注册
             new Jigsaw(
                    new JigsawStructureGenerator(
                            new JigsawPool(
                                    A_Jigsaw_Pattern::getInstance
                            ))
                            .setMode(JigsawStructureGenerator.Mode.BFS)//或JigsawStructureGenerator.Mode.DFS
                            .setLimit(50)//设置限制生成总量或深度
                            //.setWeightRandomFunction(jigsawFactorPool::getRandomObj)
                            //如需启用因子化生成，请加上上面这句
            ) {
                @Override
                public BlockPos getActualPos(JigsawSectionPos jigsawSectionPos, int i, int i1, int i2) {
                    return new BlockPos((jigsawSectionPos.x << 3) + i, (jigsawSectionPos.y << 3) + i1, (jigsawSectionPos.z << 3) + i2);
                    //设置转换关系
                }
            };
```