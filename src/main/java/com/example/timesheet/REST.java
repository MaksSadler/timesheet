package com.example.timesheet;

public class REST {

    /**
     * HTTP - протокол
     * gRPC - протокол
     * WebSockets - протокол
     *
     * Путь/эндпоинт/ручка/ресурс (это одно и тоже) - /timesheet
     *
     * REST - набор соглашений, как проектировать ресурсы вашего сервиса API
     *
     * 1. НЕ использовать глаголы в путях
     * /deleteTimesheet/{id} - плохо
     * DELETE /timesheet/{id} - хорошо
     *
     *2. Ресурсы должны вкладываться друг в друга
     * GET /users/{userId}/roles/{rolesId} - получить РОЛЬ с идентификатором roleID у юзера userId
     * GET /departments/{id}/employees/{id}/head/consultant/roles/{id}
     *
     * Получить юзеров, у которых имя содержит какую-то подстроку
     * GET /users?name-like="test1" -> 204 No Content
     * GET /users?sort-by=age&sort-order=desc
     *
     * 3. Рекомендация: использовать множественное число для ресурсов
     * Вместо /user использовать /users
     *
     * 4. Слова внутри ресурса соединяются дефисом "-"
     * GET github.com/project/pull-requests/{id}
     */
    //__________________________________________________________________________________________________
    /**
     * spring-data...
     *
     * spring-data-jdbc - зависимость которая предоставляет удобные пред настроенные инструменты
     * для работы с реляционными БД.
     *spring-data-jpa - зависимость которая предоставляет удобные пред настроенные инструменты
     * для работы с JPA
     *      spring-data-jpa
     *      /
     *     /
     *    jpa <--------hibernate(eclipselink....)
     *
     * spring-data-mongo - зависимость которая предоставляет инструменты для работы с mongo
     *
     *
     */
}
