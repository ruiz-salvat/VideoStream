import Layout from '../components/layout'
import CategoryContainer from '../components/category_container'

export default function HomePage({ categories, videos }) {
   return (
      <Layout>
            <div>
               {categories.map((category) => (
                  <CategoryContainer key={category.id} category={category} videos={videos.filter(video => video.category === category.id)}/>
               ))}
            </div>
      </Layout>
    )
}

export async function getStaticProps() {
   const categoriesRes = await fetch('http://127.0.0.1:8080/category/all')
   const videosRes = await fetch('http://127.0.0.1:8080/video/all')
   const categories = await categoriesRes.json()
   const videos = await videosRes.json()

   return {
      props: {
         categories,
         videos,
      }
   }
}