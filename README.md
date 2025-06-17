# U3_04_KDRZ
## Descripción del Proyecto

Este proyecto consiste en el desarrollo de una API REST utilizando Spring Boot (con posibilidad de usar Lombok) para gestionar un sistema de compra y venta de almacenes. El sistema permite registrar las sedes o ubicaciones de los almacenes, los propios almacenes y la información de los clientes que compran o rentan los almacenes.

### Entidades principales

- **Cedes**:  
    - `id`
    - `clave de la cede` (formato: C[id]-[ddMMyyyy]-[cuatro dígitos aleatorios])
    - `estado` (del país)
    - `municipio`

- **Almacenes**:  
    - `id`
    - `clave del almacén` (formato: [clave de la cede]-A[id])
    - `fecha de registro`
    - `precio de venta`
    - `tamaño` (G, M, P)

- **Clientes**:  
    - `id`
    - `nombre completo`
    - `número de teléfono`
    - `correo electrónico`

### Consideraciones

- El repositorio debe llamarse: `U3_04_TusIniciales`
- Se debe implementar un CRUD completo para cada entidad.
- El sistema debe cumplir al menos 4 principios de seguridad vistos en clase.
- Debe aplicar al menos 2 principios SOLID.
- Para obtener la calificación completa, el proyecto debe pasar revisión con el docente.
