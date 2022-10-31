# Argentina Prograna Proyecto Final

Back end / API Rest hecho con `Spring boot` y `java 8` utilizando ademas:

- `lombok` para simplificar el codigo de las Clases
- `Spring Security` para la Authenticacion y seguridad
- `fusionauth-jwt` para generar los Token JWT
- `Gson` para trajabar con JSON
- `Firebase Admin` para cargar y almacenar imagenes en **firebase storage**

## Base de datos

Base de datos `MySQL` de **Clever Cloud** nivel **free** de `16 MB`

Dada la poca capadidad de la base de datos las imagenes se almacenan en **firebase storage** y la base de datos solo guarda la `URL` a cada imagen

# Enlaces

- #### Aplicacion: [https://argentina-programa-backend-992.herokuapp.com](https://argentina-programa-backend-992.herokuapp.com)
- #### Front end: [https://argentina-programa-abb9b.web.app/](https://argentina-programa-abb9b.web.app/)
- #### Repositorio Front end: [https://github.com/49888/Argentina-Programa-Front-End](https://github.com/49888/Argentina-Programa-Front-End)

# End points

- [/api/banner/get](https://argentina-programa-backend-992.herokuapp.com/api/banner/get)
- [/api/education/get](https://argentina-programa-backend-992.herokuapp.com/api/education/get)
- [/api/experience/get](https://argentina-programa-backend-992.herokuapp.com/api/experience/get)
- [/api/projects/get](https://argentina-programa-backend-992.herokuapp.com/api/projects/get)
- [/api/skills/get](https://argentina-programa-backend-992.herokuapp.com/api/skills/get)


> CORS unicamente habilitado para `https://49888.github.io` y `https://argentina-programa-abb9b.web.app`