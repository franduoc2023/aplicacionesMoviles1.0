# Usamos una imagen base con Java y herramientas necesarias
FROM jenkins/inbound-agent:latest

# Cambiar a usuario root para instalar dependencias
USER root
RUN apt-get update && apt-get install -y bash wget unzip

# Definir variables de entorno para el SDK de Android
ENV ANDROID_HOME=/usr/local/android-sdk
ENV PATH=$ANDROID_HOME/cmdline-tools/latest/bin:$ANDROID_HOME/platform-tools:$ANDROID_HOME/emulator:$PATH

# Crear el directorio del SDK y descargar las herramientas de línea de comandos
RUN mkdir -p $ANDROID_HOME/cmdline-tools \
    && wget -q https://dl.google.com/android/repository/commandlinetools-linux-10406996_latest.zip -O cmdline-tools.zip \
    && unzip -q cmdline-tools.zip -d $ANDROID_HOME/cmdline-tools \
    && mv /usr/local/android-sdk/cmdline-tools/cmdline-tools /usr/local/android-sdk/cmdline-tools/latest \
    && rm cmdline-tools.zip

# Aceptar las licencias de Android SDK
RUN yes | $ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager --licenses

# Instalar las herramientas esenciales del SDK
RUN $ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager "platform-tools" "platforms;android-33" "build-tools;33.0.2"

# 🔥 SOLUCIÓN: Crear y cambiar permisos de directorios antes de cambiar usuario
RUN mkdir -p /app/.gradle \
    && chmod -R 777 /app/.gradle \
    && chown -R root:root /app/.gradle \
    && chown -R root:root /usr/local/android-sdk

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar los archivos del proyecto
COPY --chmod=755 gradlew gradlew
COPY . .

# Configurar local.properties para apuntar al SDK correcto
RUN echo "sdk.dir=/usr/local/android-sdk" > /app/local.properties

# ⚠️ IMPORTANTE: No cambiar al usuario Jenkins aún
USER root

# Ejecutar Gradle para compilar la aplicación
RUN bash /app/gradlew assembleDebug --gradle-user-home /app/.gradle


