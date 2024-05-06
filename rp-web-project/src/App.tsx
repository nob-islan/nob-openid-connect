import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Top from './components/top/Top';
import RedirectFetchToken from './components/redirectfetchtoken/RedirectFetchToken';
import RedirectAuthorization from './components/redirectauthorization/RedirectAuthorization';

function App() {
  return (
    <div>
      <BrowserRouter>
        <div>
          <Routes>
            <Route path="/top" element={<Top />} />
            <Route
              path="/redirect/fetchtoken"
              element={<RedirectFetchToken />}
            />
            <Route
              path="/redirect/authorization"
              element={<RedirectAuthorization />}
            />
          </Routes>
        </div>
      </BrowserRouter>
    </div>
  );
}

export default App;
