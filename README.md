# Автотесты перевода между картами (Page Objects)

![CI](https://github.com/K1SVJ/qa-ibank-transfer/actions/workflows/gradle.yml/badge.svg)

Проект содержит автотесты функции перевода с карты на карту с использованием Page Object.

## Технологии
- Java 11
- Selenide (Page Object pattern)
- JUnit 5
- GitHub Actions (CI)



## Запуск тестов
```bash
./gradlew test -Dselenide.headless=true
