在JPA中，这里是可以映射的有效简单类型：

- 原始Java类型：byte，int，short，long，boolean，char，float，double
- 原始Java类型的包装类：Byte，Integer，Short，Long，Boolean，Character，Float，Double
- 字节和字符数组类型：byte []，Byte []，char []，Character []
- 大数字类型：java.math.BigInteger，java.math.BigDecimalStrings：
- java.lang.String中
- Java时态类型：java.util.Date，java.util.Calendar
- JDBC时态类型：java.sql.Date，java.sql.Time，java.sql.Timestamp
- 枚举类型：任何系统或用户定义的枚举类型
- Serializable 对象：任何系统或用户定义的Serializable 类型


所以在jpa中如果有自己定义的@Entity中有嵌套类,则需要实现Serializable