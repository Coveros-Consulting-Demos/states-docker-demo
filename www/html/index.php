<!--
Copyright:: 2018, Coveros
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
-->
<html>
	<head>
		<title>US States</title>
		<style>
			table,th,td { border: 1px solid black; border-collapse: collapse; }
			th,td { padding: 5px; }
		</style>
	</head>

	<body>
		<h1>US States</h1>

		<?php
			error_reporting(E_ALL);

			try {
				# connect to redis
				$redis = new Redis();
				$redis->connect('redis');

				# get the counter
				$redis->incr('visits');
				$visits = $redis->get('visits');

				# get the states hash
				$states = $redis->hgetall('states');
				ksort($states);

				$redis->close();
			} catch (Exception $e) {
				die($e->getMessage());
			}
		?>

                <p>This page has had <?= $visits ?> visits so far.</p>

		<table>
			<thead>
				<tr>
					<th>Abbreviation</th>
					<th>State</th>
				</tr>
			</thead>
			
			<tbody>
				<?php foreach ($states as $abbr => $name) { ?>
					<tr><td><?= $abbr ?></td><td><?= $name ?></td></tr>
				<?php } ?>
			</tbody>
		</table>
	</body>
</html>
