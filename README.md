# Password Strength Checker

A Maven-based application that evaluates password strength - available in both **CLI** and **GUI** versions!

## Features
- ✅ **Zero dependencies** - Uses only Java standard library
- ✅ **20 lines of code** - Ultra-minimal implementation
- ✅ **Comprehensive checks**:
  - Password length (8+ chars, 12+ chars)
  - Special characters (!@#$%^&*()_+-=[]{}etc.)
  - Numbers (0-9)
  - Uppercase and lowercase letters

## Strength Levels
- **WEAK** (0-2 points): Missing multiple security criteria
- **MEDIUM** (3 points): Meets some security requirements
- **STRONG** (4-5 points): Meets most or all security requirements

## How to Run

### CLI Version (App.java)

#### Option 1: Using Maven (Interactive)
```bash
mvn compile exec:java -Dexec.mainClass="bhavya.App"
```

### Option 2: Using Maven (Quiet mode)
```bash
mvn compile exec:java -Dexec.mainClass="bhavya.App" -q
```

### Option 3: Build and run JAR
```bash
mvn clean package
java -cp target/devopsproject-1.0.jar bhavya.App
```

### GUI Version (PasswordCheckerGUI.java)

**Run the graphical interface:**
```bash
mvn compile exec:java -Dexec.mainClass="bhavya.PasswordCheckerGUI"
```

**GUI Features:**
- 🎨 **Modern, attractive interface** with color-coded feedback
- ⚡ **Real-time password checking** as you type
- 📊 **Visual progress bar** showing password strength
- ✅ **Live criteria checklist** with checkmarks
- 👁️ **Show/Hide password** toggle
- 🎯 **Color indicators**:
  - 🔴 Red = WEAK
  - 🟡 Yellow = MEDIUM
  - 🟢 Green = STRONG

## Examples

### CLI Examples

**Weak password:**
```
Enter password: abc
Password strength: WEAK
Score: 0/5
```

**Medium password:**
```
Enter password: Password123
Password strength: MEDIUM
Score: 3/5
```

**Strong password:**
```
Enter password: MyP@ssw0rd123!
Password strength: STRONG
Score: 5/5
```

## Scoring System
Each criterion adds 1 point (max 5):
1. Length ≥ 8 characters
2. Length ≥ 12 characters
3. Contains both uppercase AND lowercase letters
4. Contains at least one number
5. Contains at least one special character
