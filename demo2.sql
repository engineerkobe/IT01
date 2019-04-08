-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: localhost    Database: demo2
-- ------------------------------------------------------
-- Server version	5.7.25-0ubuntu0.16.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `poster` varchar(255) DEFAULT NULL,
  `rootid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `pdate` datetime DEFAULT NULL,
  KEY `poster` (`poster`),
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`poster`) REFERENCES `userinfo` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES ('root',1,0,'frist','Hello world','2019-03-13 05:09:58');
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `isbn_book`
--

DROP TABLE IF EXISTS `isbn_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `isbn_book` (
  `isbn` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `count` int(11) NOT NULL,
  PRIMARY KEY (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `isbn_book`
--

LOCK TABLES `isbn_book` WRITE;
/*!40000 ALTER TABLE `isbn_book` DISABLE KEYS */;
INSERT INTO `isbn_book` VALUES ('9787111389187','深入理解Android ：卷Ⅱ','\n	《深入理解Android:卷2》是“深入理解Android”系列的第2本，第1本书上市后获得广大读者高度评价，在Android开发者社群内口口相传。《深入理解Android:卷2》不仅继承了第1本书的优点并改正了其在细微处存在的一些不足，而且还在写作的总体思想上进行了创新，更强调从系统设计者的角度去分析Android系统中各个模块内部的实现原理和工作机制。从具体内容上讲，重点是Android Framework的Java层，对Java层涉及的核心模块和服务进行了深入而细致的分析。通过《深入理解Android:卷2》，读者不仅能对Android系统本身有更深入的理解，而且还能掌握分析大型复杂源代码的能力。\n\n《深入理解Android:卷2》共8章：第1章介绍了阅读本书所需要做的准备工作，包括Android 4.0源码的下载和编译、Eclipse环境的搭建，以及Android系统进程（system_process）的调试等；第2章对Java Binder和MessageQueue的实现进行了深入分析；第3章仔细剖析了SystemServer的工作原理，这些服务包括EntropyService、DropboxManagerService、DiskStatsService、DeviceStorageMonitorService、SamplingProfilerService和ClipboardService；第4章对系统中负责Package信息查询和APK安装、卸载、更新等工作的服务PackageManagerService进行了详细分析；第5章则对Android系统中负责电源管理的核心服务 PowerManagerService的原理进行了一番深入的分析；第6章以ActivityManagerService为分析重点，它的启动、Activity的创建和启动、BroadcastReceiver的工作原理、Android中的进程管理等内容展开了较为深入的研究；第7章对ContentProvider的创建和启动、SQLite、Cursor query和close的实现等进行了深入分析；第8章以ContentService和AccountManagerService为分析对象，介绍了数据更新通知机制的实现，以及账户管理和数据同步等相关知识。\n	',1),('9787111390589','Android开发精要','\n	《Android开发精要》如何才能写出贴近Android设计理念、能够更加高效和可靠运行的Android应用？通过Android的源代码去了解其底层实现细节是最重要的方法之一！然而，Android系统太过于庞大，源代码实现复杂，学习的技术门槛和时间成本都很高。有没有一种方法既能帮助开发者深入理解Android应用开发，又能不被底层大量的实现细节所羁绊，迅速掌握编写高质量Android应用所需的知识？《Android开发精要》针对这个问题给出了完美的答案！它从Android繁杂的源代码中抽取出了Android开发的“精华”和“要点”，剥离了大量琐碎的底层实现细节，进行了高度概括和总结，不仅能帮助开发者迅速从宏观上理解整个Android系统的设计理念，而且能帮助开发者迅速从微观上掌握核心知识点的原理，从而编写出高质量的Android应用。\n\n《Android开发精要》共13章，在逻辑上分为4个部分。第一部分（1-2章）：第1章系统介绍了Android的系统架构、核心模块和设计思想，旨在让读者真正理解它的设计理念；第2章讲解了Android源代码的获取、编译、阅读和编辑的方法。第二部分（3-6章）：第3章深入阐述了Android组件机制的设计理念和重要特征，并详细介绍了4大组件的方法和原理；第4章讲解了Intent对象和Android的意图机制，阐明了Android是如何将来自不同应用、不同进程的组件整合在一起的；第5章解析了Android中各个组件的生命周期，包括组件的进程模型和线程模型；第6章从开发的角度详细阐述了组件间数据传递的解决方案，以及它们的优缺点和适用场景。第三部分（7-8章）：第7章深入讲解了Android的控件框架，结合实际项目对重要控件的实现和使用逐一进行了分析，还包含Android 4.0界面开发的实践“精华”；第8章分析了Android的资源体系，剖析了Android底层对资源的处理。第4部分（9-13章）：第9章讲解了Android的数据存储结构，以及不同数据存储模式的使用要点；第10章分析了Android的各种网络连接方式，涵盖NFC和基于Wifi的P2P连接等内容；第11章讲解了Android的定位服务、地址解析服务、地图服务的框架实现；第12章仔细分析了Android各种多媒体功能的实现机制；第13章对Android中比较有特色的一些模块的实现细节进行了分析。\n	',1),('9787111417132','Android的设计与实现：卷I','\n	本书是Android应用开发工程师和Android系统工程师进阶修炼的必读之作。它由资深Android内核专家亲自执笔，从源代码角度，系统、深入、透彻剖析Android系统框架层（Framework）的设计思想和实现原理，为Android应用工程师和系统工程师解决实际工作中的各种难题提供了原理性的指导。为了降低读者的阅读成本，本书使用了大量简单的UML类图和序列图来展示类的层次结构和方法的调用流程，使读者能迅速读完本书并领会其精髓！\n\n“Android的设计与实现”系列丛书主要围绕Android系统的四层结构展开，通过源代码来分析各层的设计思想与实现原理，卷I则主要是针对Framework（框架层）的。全书共12章，分为六个部分：基础篇（第1～2章）详细讲解了Android的体系结构、源代码阅读和调试环境的搭建，以及整个框架的基础；启动篇（第3～4章）深入分析了Android启动过程的机制和实现原理，能帮助读者全面理解框架层系统服务的运行基础；Binder篇（第5～6章）着重分析了Binder在Native框架层和Java框架层的机制和实现，能让读者深入理解进程间的通信模型；消息通信篇（第7章）重点分析了Android的消息驱动和异步处理机制，能让读者深入理解线程间的通信模型；Package Manager篇（第8～9章）主要讲解了Package Manager的机制与实现，以及APK的安装方法与过程；Activity Manager篇（第10～12章）深入阐述了ActivityManagerService的运行机制、应用程序和进程的启动流程，以及进程管理机制。\n\n本书适合中高级的Android应用开发工程师、Android系统开发工程师、Android系统架构师，以及负责对Android系统进行调试和优化的工程师们阅读。\n	',2),('9787111500698','深入应用C++11','\n	在StackOverflow的最近一次世界性调查中，C++11在所有的编程语言中排名第二， C++11受到程序员的追捧是毫不意外的，因为它就像C++之父Bjarne Stroustrup说的：它看起来就像一门新的语言。C++11新增加了相当多的现代编程语言的特性，相比C++98/03，它在生产力、安全性、性能和易用性上都有了大幅提高。比如auto和decltype让我们从书写冗长的类型和繁琐的类型推断中解脱出来，智能指针让我们再也不用担心内存泄漏的问题，右值引用避免了无谓的临时对象拷贝，type_traits和可以变模板参数让我们可以更方便地写更加泛化的代码……这些还只是新增的一百多项新特性中的一小部分而已。C++14的标准在2014年的时候已经确定，而C++17标准正在制定当中，可以预见的未来，C++的发展会越来越好，C++的应用会越来越广泛，也会更受广大开发者地欢迎。\n\n本书的内容来源于C++11项目实践，写作此书的目的是为了和广大读者分享学习和应用C++11的经验和乐趣。本书分为两篇，第一篇主要是介绍一些常用的C++11特性，介绍这些特性是如何优化C++11以前的一些代码的，读者可以从中更深刻地领悟这些新特性；第二篇主要是一些实际开发中的典型应用案例，通过这些案例读者可以看到C++11的这些新特性是如何综合运用于实际开发中的，具有实践的指导作用。相信本书会成为读者学习和应用C++11的良师益友。\n	',1),('9787115215536','高效程序员的45个习惯敏捷开发修炼之道','\n	“书中‘切身感受’的内容非常有价值——通过它我们可以做到学有所思，思有所悟，悟有所行。”\n\n——Nathaniel T. Schutta，《Ajax基础教程》作者\n\n“此书通过常理和经验，阐述了为什么你应该在项目中使用敏捷方法。最难得的是，这些行之有效的实战经验，竟然从一本书中得到了。”\n\n——Matthew Johnson，软件工程师\n\n十年来，软件行业发生了翻天覆地的变化。敏捷方法大行其道，测试和测试驱动开发在很多开发人员的工作中扮演着重要的角色。作为一名程序员，你应该培养怎样的素质，方能对多变的环境应对自如，始终立于不败之地？\n\n本书简明实用、见解深刻，总结了高效程序员在开发过程中的45个个人习惯、思想观念和方法，有助于开发人员在开发进程、编码工作、开发者态度、项目和团队管理，以及持续学习等5个方面积极修炼。通过学习这些内容，养成这些好的习惯，你可以极大地提升自己的编程实力，更快速、更可靠地交付更高质量的软件，从而成为真正的高效程序员。\n	',2),('9787115267276','布道之道:引领团队拥抱技术创新','\n	《布道之道:引领团队拥抱技术创新》旨在告诉读者如何说服自己的同事采用新的工具和技术。作者总结了7种怀疑论者模式：孤陋寡闻型、随波逐流型、百般挑剔型、激情燃尽型、时间紧迫型、发号施令型和不可理喻型。围绕说服这些怀疑论者，书中介绍了9个实用技巧和4个策略。理解并熟练运用这些技巧和策略，能够让你的技术布道生涯收获累累硕果。《布道之道:引领团队拥抱技术创新》适合IT行业的技术布道师、推广专家、产品经理、需求调研及实施人员阅读，同样也适合对前沿技术时刻保持深厚兴趣的设计和开发人员参考。\n	',1),('9787115387134','Python高手之路','\n	这不是一本常规意义上Python的入门书。这本书中没有Python关键字和for循环的使用，也没有细致入微的标准库介绍，而是完全从实战的角度出发，对构建一个完整的Python应用所需掌握的知识进行了系统而完整的介绍。更为难得的是，本书的作者是开源项目OpenStack的PTL（项目技术负责人）之一，因此本书结合了Python在OpenStack中的应用进行讲解，非常具有实战指导意义。\n\n《Python高手之路》从如何开始一个新的项目讲起，首先是整个项目的结构设计，对模块和库的管理，如何编写文档，进而讲到如何分发，以及如何通过虚拟环境对项目进行测试。此外，《Python高手之路》还涉及了很多高级主题，如性能优化、插件化结构的设计与架构、Python 3的支持策略等。\n	',2),('9787121181085','Android系统源代码情景分析','\n	在内容上，本书结合使用情景，全面、深入、细致地分析Android系统的源代码，涉及到Linux内核层、硬件抽象层（HAL）、运行时库层(Runtime)、应用程序框架层(Application Framework)以及应用程序层(Application)。\n\n在组织上，本书将上述内容划分为初识Android系统、Android专用驱动系统和Android应用程序框架三大篇章。初识Android系统篇介绍了参考书籍、基础知识以及实验环境搭建；Android专用驱动系统篇介绍了Logger日志驱动程序、Binder进程间通信驱动程序以及Ashmem匿名共享内存驱动程序；Android应用程序框架篇从组件、进程、消息以及安装四个维度来对Android应用程序的框架进行了深入的剖析。\n\n通过上述内容及其组织，本书能使读者既能从整体上把握Android系统的层次结构，又能从细节上去掌握每一个层次的要点。\n	',1);
/*!40000 ALTER TABLE `isbn_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rfid_book`
--

DROP TABLE IF EXISTS `rfid_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rfid_book` (
  `rfid` varchar(255) NOT NULL,
  `is_borrow` varchar(1) NOT NULL,
  `borrower` varchar(255) NOT NULL,
  `log` datetime NOT NULL,
  `isbn` varchar(255) NOT NULL,
  PRIMARY KEY (`rfid`),
  KEY `isbn` (`isbn`),
  CONSTRAINT `rfid_book_ibfk_1` FOREIGN KEY (`isbn`) REFERENCES `isbn_book` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rfid_book`
--

LOCK TABLES `rfid_book` WRITE;
/*!40000 ALTER TABLE `rfid_book` DISABLE KEYS */;
INSERT INTO `rfid_book` VALUES ('0A309600','0','0','1900-01-01 00:00:00','9787111390589'),('0A397900','0','0','1900-01-01 00:00:00','9787121181085'),('0A5A2500','0','0','1900-01-01 00:00:00','9787115215536'),('0A725000','0','0','1900-01-01 00:00:00','9787115215536'),('0A752E00','0','0','1900-01-01 00:00:00','9787111417132'),('0A9F6300','0','0','1900-01-01 00:00:00','9787111389187'),('0AFF3F00','0','0','1900-01-01 00:00:00','9787115387134'),('5593F4E6','0','0','1900-01-01 00:00:00','9787115387134'),('963E971D','0','0','1900-01-01 00:00:00','9787115267276'),('96BFAA1D','0','0','1900-01-01 00:00:00','9787111500698'),('A623061D','0','0','1900-01-01 00:00:00','9787111417132');
/*!40000 ALTER TABLE `rfid_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinfo`
--

DROP TABLE IF EXISTS `userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userinfo` (
  `account` varchar(255) NOT NULL,
  `password` varchar(50) NOT NULL,
  `head_sticker` text,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinfo`
--

LOCK TABLES `userinfo` WRITE;
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` VALUES ('root','123',NULL,'MIKU'),('test1','test1',NULL,NULL);
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-21 12:28:31
