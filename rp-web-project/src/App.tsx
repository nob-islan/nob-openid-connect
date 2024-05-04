import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Top from './components/top/Top';
import RedirectFetchToken from './components/redirectfetchtoken/RedirectFetchToken';

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
          </Routes>
        </div>
      </BrowserRouter>
    </div>
  );
}

export default App;
