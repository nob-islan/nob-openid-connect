import ReactDOM from 'react-dom/client';
import './index.module.scss';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { Provider } from 'react-redux';
import {
  applyMiddleware,
  combineReducers,
  legacy_createStore as createStore
} from 'redux';
import { reducers } from './reducers';
import { thunk } from 'redux-thunk';
import { CookiesProvider } from 'react-cookie';

// 各種reducer, stateを用意
const rootReducer = combineReducers(reducers);
export type RootState = ReturnType<typeof rootReducer>;
export const store = createStore(rootReducer, applyMiddleware(thunk));

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <Provider store={store}>
    <CookiesProvider>
      <App />
    </CookiesProvider>
  </Provider>
);

reportWebVitals();
