import Layout from '../components/layout'
import CategoryContainer from '../components/index/category_container'

export default function HomePage({ categories, videos }) {
   function testButton() {
      fetch(`${process.env.NEXT_PUBLIC_LOCALHOST_URL}video/details/sinkin_bathtub`, {
         credentials: "include",
         mode: "cors",
         headers: {
            'Access-Control-Allow-Origin': `http://165.22.88.112:80`
         }
      })
      .then(response => response.json())
      .then(response => {
         console.log("Response", response);
      })
   }

   return (
      <Layout>
            <div>
               <button onClick={testButton}>Test Button</button>
               Hola
            </div>
            <div>
               {categories.map((category) => (
                  <CategoryContainer key={category.id} category={category} videos={videos.filter(video => video.category === category.id)}/>
               ))}
            </div>
      </Layout>
    )
}

export async function getStaticProps() {
   const categoriesRes = await fetch(`${process.env.API_URL}category/all`)
   const videosRes = await fetch(`${process.env.API_URL}video/all`)
   const categories = await categoriesRes.json()
   const videos = await videosRes.json()

   return {
      props: {
         categories,
         videos,
      }
   }
}