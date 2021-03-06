# XML_RPC_TEST
Консольное клиент-серверное приложение, реализующее функцию словаря. Словарь хранится на сервере, в памяти приложения, в единственном экземпляре. Таким образом серверное приложение имеет один общий словарь для всех клиентов.

Пользователь запускает клиентское приложение со следующими параметрами:

	* имя/ip-адрес сервера;

	* порт сервера (по желанию, можно сделать фиксированным);

	* команда;

	* аргументы команды.

Пример: java -jar client.jar 192.168.0.1 add hello алло привет здравствуйте

Приложение обрабатывает аргументы и посылает соответствующий запрос. Если команда что-то возвращает, то приложение выводит результат на экран и завершает работу. Все исключения и ошибки должны обрабатываться и выводиться соответствующие сообщения об ошибках.

Серверное приложение должно слушать фиксированный либо задаваемый пользователем порт.

Каждое слово в словаре может иметь несколько значений, но значения конкретного слова не должны повторятся. Например слово 'man' может иметь значения 'человек' и 'мужчина', но не может иметь значения 'мужчина' и 'мужчина'. Сервер должен обрабатывать каждое клиентское подключение в отдельном потоке, так что необходимо позаботиться об одновременном доступе к словарю нескольких потоков.

После выполнения запроса клиента сервер разрывает соединение.

Допустимые команды

	add <слово> <значение1> [<значение2> ...] -- добавляет в словарь указанные значения слова, сохраняя при это старые

		Пример: add hello алло привет здравствуйте

		Вывод: <значения слова успешно добавлены>

	get <слово> -- возвращает значения слова, каждое слово должно начинаться с новой строки. В случае если искомого слова в словаре не содержится, приложение должно сообщить об этом.

		Пример 1 (слово есть в словаре): get hello Вывод: привет здравствуйте 
		Пример 2 (слова нет в словаре): get hello2 Вывод: <слово отсутвует в словаре>

	delete <слово> <значение1> [<значение2> ...] -- удаляет из словаря указанные значения слова

		Пример 1 (слово есть в словаре): delete hello привет Вывод: <значения слова успешно удалены> 
		Пример 2 (слова/значения нет в словаре): delete hello пока Вывод: <слово/значение отсутвует в словаре>
	
	list -- показывает уже добавленные слова в словарь.

Реализация клиент-серверного протокола. В качестве реализации протокола возможен свой протокол, XML-RPC (библиотека Apache XML-RPC) или сериализация объектов (классы ObjectOutputStream, ObjectInputStream).
