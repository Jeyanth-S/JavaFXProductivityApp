# 📋 JavaFX Productivity App

A sleek task management tool built with JavaFX, powered by a PostgreSQL backend and custom configuration handling. Designed to enhance daily efficiency through intuitive UI and persistent task logic.

---

## 🚀 Features

- 🧠 **Daily Task Logic:** Automatically loads tasks based on the current date  
- 🛢️ **PostgreSQL Integration:** Smooth JDBC handling with persistent storage  
- 🧰 **Custom Config Loader:** Keeps sensitive DB credentials out of source code  
- 🔋 **Shell Script Integration:** Includes battery monitoring script enhancements  
- 🎨 **JavaFX UI:** Responsive interface designed with modular FXML components  

---

## 🛠️ Prerequisites

- Java 21 (or compatible with JavaFX SDK)  
- Maven  
- JavaFX SDK installed at `~/javafx-sdk-21/` or update path in run script  
- PostgreSQL running locally  
- `config.properties` placed next to `pom.xml` with valid DB credentials

---

## 🧪 Running the App (Dev Mode)

```bash
mvn clean javafx:run
```

---

## 📦 Build and Launch (Packaged JAR)

### Step 1: Package the app

```bash
mvn clean package
```

### Step 2: Run with custom launcher

```bash
#!/bin/bash
java --module-path path/tojavafx/modules \
     --add-modules path/to/java/fx \
     -jar "path/to/compiled/jar"
```

💡 *Make sure to update the module path and JAR location if needed.*

---

## 🗂️ Project Structure

```bash
JavaFXProductivityApp/
├── pom.xml
├── config.properties
├── src/
│   └── main/
│       ├── java/
│       │   ├── com/jeyanth/ui/
│       │   └── com/jeyanth/config/
│       └── resources/
│           ├── fxml/
│           └── images/
├── battery_sentinel.sh
```

---

## 🧾 License & Attribution

This project is a personal productivity initiative by **Jeyanth**.  
Feel free to fork, explore, and evolve—creativity and efficiency welcome.
