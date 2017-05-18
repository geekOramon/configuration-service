import requests
import argparse
import pprint


def read_arguments():
    parser = argparse.ArgumentParser()
    parser.add_argument('--url', help="App url")
    parser.add_argument('--user', help="Authentication user")
    parser.add_argument('--password', help="Authentication password")

    args = parser.parse_args()

    params.update({"user": args.user})
    params.update({"password": args.password})
    params.update({"url": args.url})


if __name__ == "__main__":
    params = {}
    read_arguments()

    result = requests.get(params.get("url"), auth=(params.get("user"), params.get("password")))

    printer = pprint.PrettyPrinter(depth=6)
    print(result)
    print(result.status_code)
    printer.pprint(result.json())
