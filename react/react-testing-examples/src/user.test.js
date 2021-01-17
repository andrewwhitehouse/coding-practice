import React from 'react';
import {act} from "react-dom/test-utils";
import {render, unmountComponentAtNode} from "react-dom";
import User from "./user";

let container = null;
beforeEach(() => {
  container = document.createElement('div');
  document.body.appendChild(container);
});

afterEach(() => {
  unmountComponentAtNode(container);
  container.remove();
  container = null;
});

const setupFetchStub = (data) => {
  return function fetchStub(_url) {
    return new Promise((resolve) => {
      resolve({
        json: () =>
          Promise.resolve({
            data,
          }),
      })
    })
  }
}

it("renders user data", async () => {
  const fakeUser = {
    name: "Joni Baez",
    age: 32,
    address: "38, Acacia Avenue"
  };

  // https://jaketrent.com/post/mock-fetch-jest-test/
  global.fetch = jest.fn().mockImplementation(setupFetchStub(fakeUser));

  // Use the asynchronous version of act to apply resolved promises
  await act(async () => {
    render(<User id="123" />, container);
  });

  expect(container.querySelector("summary").textContent).toBe(fakeUser.name);
  expect(container.querySelector("strong").textContent).toBe(`${fakeUser.age}`);
  expect(container.textContent).toContain(fakeUser.address);

  global.fetch.mockClear();
});