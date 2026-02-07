# jdbc-tricks

## Deep Dive into JDBC Security: Special URL Construction and Non-Networked Deserialization Exploitation Techniques Revealed

![JDBC](https://img.shields.io/badge/JDBC-Security-red)
![MySQL](https://img.shields.io/badge/MySQL-Driver-blue)
![Research](https://img.shields.io/badge/Security-Research-green)

This project summarizes JDBC security research findings, focusing on special URL construction techniques and methods for
non-networked deserialization exploitation.

Presentation PPT attachment:
[Deep Dive into JDBC Security: Special URL Construction and Non-Networked Deserialization Exploitation Techniques Revealed.pptx](深入JDBC安全：特殊URL构造与不出网反序列化利用技术揭秘.pptx)

## Project Structure

```-
jdbc-tricks/
├── LICENSE
├── README.md
├── dump-mysql-properties/        # MySQL driver default security property analysis
├── jdbc-test-case/               # JDBC test case collection
│   ├── mysql-driver/             # MySQL Driver default properties and bypass techniques for different versions
│   ├── h2-driver/                # H2 JDBC special URL construction and non-networked exploitation
│   ├── postgreSQL-driver/        # PostgreSQL JDBC PoCs and exploitation tests
│   ├── jaas4jdbc/                # JDBC attack surface leveraging JAAS
│   └── utils/                    # Common utility classes (e.g., MysqlConnectionUtils)
├── jdbc-url-fuzzer/              # JDBC URL fuzzing framework
├── real-world-case/              # Collection of real-world vulnerability cases
```

## 🔍 Tricks List

### MYSQL Driver Tricks

Known tricks:

- default properties: Default property bypass
    - [DefaultProperties.java](jdbc-test-case/mysql-driver/version8/src/main/java/com/jdbc/tricks/default_properties/DefaultProperties.java)
- multi host: Multiple host syntax bypass
    - [Connection URL Syntax](https://dev.mysql.com/doc/connector-j/en/connector-j-reference-jdbc-url-format.html#connector-j-url-user-credentials)
    - [AllowLoadLocal_MultiHostInjectionBypass.java](jdbc-test-case/mysql-driver/version8/src/main/java/com/jdbc/tricks/multi_host/AllowLoadLocal_MultiHostInjectionBypass.java)
- space between: Key-value space insertion bypass
    - [AllowLoadLocal_SpaceBetweenKeyValueBypass.java](jdbc-test-case/mysql-driver/version8/src/main/java/com/jdbc/tricks/space_between/AllowLoadLocal_SpaceBetweenKeyValueBypass.java)
- tab between: Key-value tab character insertion bypass
    - [AllowLoadLocal_TabBetweenKeyValueBypass.java](jdbc-test-case/mysql-driver/version8/src/main/java/com/jdbc/tricks/space_between/AllowLoadLocal_TabBetweenKeyValueBypass.java)
- upper case: Key-value uppercase bypass
    - [AllowLoadLocal_TrueUpperCaseBypass.java](jdbc-test-case/mysql-driver/version8/src/main/java/com/jdbc/tricks/upper_case/AllowLoadLocal_TrueUpperCaseBypass.java)
- address block parameter ：Bypassing parameters using brackets
    - [AllowLoadLocal_AddressBlockParameterBypass.java](jdbc-test-case/mysql-driver/version8/src/main/java/com/jdbc/tricks/address_block_parameter/AllowLoadLocal_AddressBlockParameterBypass.java)

Conference public content:

- no-outbound: jdbc non-networked exploitation
    - [no-outbound/README.md](jdbc-test-case/mysql-driver/no-outbound/README.md)
- no-outbound-spring: jdbc non-networked exploitation in Spring environments
    - [no-outbound-spring/README.md](jdbc-test-case/mysql-driver/no-outbound-spring/README.md)
- multi-host and equalsIgnoreCase bypass
    - [AllowLoadLocal_MultiHost_equalsIgnoreCase_bypass](jdbc-test-case/mysql-driver/version8/src/main/java/com/jdbc/tricks/multi_host/AllowLoadLocal_MultiHost_equalsIgnoreCase_bypass.java)
    - [equalsIgnoreCase bypass key fuzz case](jdbc-test-case/mysql-driver/version8/src/main/java/com/jdbc/tricks/multi_host/fuzzCase1.java)
- other-between
    - [AllowLoadLocal_OtherBetweenKeyValueBypass.java](jdbc-test-case/mysql-driver/version8/src/main/java/com/jdbc/tricks/space_between/AllowLoadLocal_OtherBetweenKeyValueBypass.java)
    - [Whitespace character fuzz case](jdbc-test-case/mysql-driver/version8/src/main/java/com/jdbc/tricks/space_between/fuzzCase2.java)
- QuoteBypass
    - [QuoteBypass.java](jdbc-test-case/mysql-driver/version5/src/main/java/com/jdbc/tricks/quote_bypass/QuoteBypass.java)
- ReplicationBypass
    - [ReplicationBypass.java](jdbc-test-case/mysql-driver/version5/src/main/java/com/jdbc/tricks/replication/ReplicationBypass.java)


Non-conference public content:

- bypass_max_allowed_packet 5.1.16 version example
    - [Bypassing max_allowed_packet parameter](jdbc-test-case/mysql-driver/version5/src/main/java/com/jdbc/tricks/default_properties/README.md)
    - [DefaultProperties.java](jdbc-test-case/mysql-driver/version5/src/main/java/com/jdbc/tricks/default_properties/DefaultProperties.java)
    - [bypass_max_allowed_packet.py](jdbc-test-case/mysql-driver/version5/src/main/java/com/jdbc/tricks/default_properties/bypass_max_allowed_packet.py)

### Other Driver Tricks

#### H2 JDBC Tricks

- Keyword bypass
  - Backslash bypass
    - [BackslashBypass](jdbc-test-case/h2-driver/src/main/java/com/jdbc/tricks/BackslashBypass.java)
  - Case bypass
    - [CaseConversionBypass](jdbc-test-case/h2-driver/src/main/java/com/jdbc/tricks/CaseConversionBypass.java)
  - Unicode bypass
    - [UnicodeBypass](jdbc-test-case/h2-driver/src/main/java/com/jdbc/tricks/UnicodeBypass.java)
- Special fearure
  - Using invisible characters to replace spaces
    - [NoSpacePoc](jdbc-test-case/h2-driver/src/main/java/com/jdbc/tricks/NoSpacePoc.java)
  - RUNSCRIPT FROM without .sql and connect directly to the IP address
    - [NoSqlSuffix](jdbc-test-case/h2-driver/src/main/java/com/jdbc/tricks/NoSqlSuffix.java)
  - RUNSCRIPT FROM connect to local file
    - [ConnectToLocalFiles](jdbc-test-case/h2-driver/src/main/java/com/jdbc/tricks/ConnectToLocalFiles.java)
  - No network poc automatically generates script
    - [NoNetworkConversion](jdbc-test-case/h2-driver/NoNetworkConversion/)

#### PostgreSQL JDBC Tricks

- JAAS PoC example
  - [App.java](jdbc-test-case/postgreSQL-driver/src/main/java/com/jdbc/tricks/App.java)

#### JAAS for JDBC Tricks

- Databricks JDBC JAAS exploitation example
  - [Databricks.java](jdbc-test-case/jaas4jdbc/src/main/java/databricks/Databricks.java)
- Impala JDBC JAAS exploitation example
  - [Impala.java](jdbc-test-case/jaas4jdbc/src/main/java/impala/Impala.java)

#### JDBC Utils

- MySQL connection utility class (helps construct and reuse PoCs)
  - [MysqlConnectionUtils.java](jdbc-test-case/utils/src/main/java/com/jdbc/tricks/utils/MysqlConnectionUtils.java)

## 🔥 Real-World Vulnerability Cases

Case outline:
[real-world-case/README.md](real-world-case/README.md)

- 2025-04-20 L0ne1y contributed case collection
  [real-world-case/2025-04-20-L0ne1y](real-world-case/2025-04-20-L0ne1y)
- 2025-06-23 fushuling contributed case collection [real-world-case/2025-06-23-fushuling](real-world-case/2025-06-23-fushuling)

## Other Excellent Open-source JDBC Projects

tricks:
- cwkiller [JDBC-PROXY-Bypass](https://github.com/cwkiller/JDBC-PROXY-Bypass) : Bypassing JDBC Attack Detection with Proxy Driver

JDBC tools:
- rmb122 [rogue_mysql_server](https://github.com/rmb122/rogue_mysql_server) : A rouge mysql server supports reading files from most mysql libraries of multiple programming languages.


## 🤝 Contribution Guidelines

Contributions of new JDBC security research findings are welcome! Please follow these steps:

1. Fork this project
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## ⭐ Star History

[![Star History Chart](https://api.star-history.com/svg?repos=yulate/jdbc-tricks&type=Date)](https://www.star-history.com/#yulate/jdbc-tricks&Date)

## 📄 License

This project follows the provisions of the [LICENSE](LICENSE) file in the project root.

---

⚠️ **Disclaimer**: This project is for security research and educational purposes only. Please conduct testing in
legally authorized environments.
